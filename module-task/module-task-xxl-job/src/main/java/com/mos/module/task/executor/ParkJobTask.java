package com.mos.module.task.executor;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @date: 2020/9/10 15:54
 * @author: wangchaodz@gmail.com
 */
@Component
public class ParkJobTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkJobTask.class);

    static List<String> cars = new ArrayList<>();

    static {
        cars.add("oPKEs1DRkw9A7ggNQtvCE5kWOKpg,鲁B1D4D8");
    }

    @XxlJob("parkJobHandler")
    public ReturnT<String> parkJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        boolean isOk = true;
        for (String car : cars){
            String openid = car.split(",")[0];
            String carno = car.split(",")[1];
            boolean singleFlag = pay(openid, carno);
            if (!singleFlag){
                isOk = false;
            }
        }
        return isOk ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    private boolean pay(String openid, String carno) {
        try {
            //创建订单
            String orderStr = createOrder(openid, carno);
            if (StringUtils.isEmpty(orderStr)) {
                LOGGER.error("创建订单失败,{},{}", carno, orderStr);
                XxlJobLogger.log("创建订单失败,{},{}", carno, orderStr);
                return false;
            }
            JSONObject orderJson = JSONObject.parseObject(orderStr);
            int code = orderJson.getInteger("code");
            String msg = orderJson.getString("msg");
            if (code != 1) {
                LOGGER.error("创建订单失败,{},{}", carno, msg);
                XxlJobLogger.log("创建订单失败,{},{}", carno, msg);
                return false;
            }
            JSONObject data = orderJson.getJSONObject("data");
            String orderno = data.getString("order_no");

            //支付
            String payStr = doPay(openid, orderno);
            if (StringUtils.isEmpty(payStr)) {
                LOGGER.error("支付订单失败,{},{}", carno, payStr);
                XxlJobLogger.log("支付订单失败,{},{}", carno, payStr);
                return false;
            }
            JSONObject payJson = JSONObject.parseObject(payStr);
            code = payJson.getInteger("code");
            msg = payJson.getString("msg");
            if (code != 1) {
                LOGGER.error("支付订单失败,{},{}", carno, msg);
                XxlJobLogger.log("支付订单失败,{},{}", carno, msg);
                return false;
            }
            LOGGER.error("支付订单成功,{},{}", carno, payStr);
            XxlJobLogger.log("支付订单成功,{},{}", carno, payStr);
            return true;
        } catch (Exception e) {
            LOGGER.error("支付订单异常,{}", carno, e);
            XxlJobLogger.log("支付订单,{},{}", carno, e.getMessage());
            return false;
        }
    }

    private String createOrder(String openid, String carno) throws IOException {
        carno = URLEncoder.encode(carno, "UTF-8");
        String url = "http://www.n-park.net/api/staff/createCarOrder?carNo=" + carno + "&useCouponId=0&openid=" + openid;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body() == null ? null : response.body().string();
    }

    private String doPay(String openid, String orderno) throws IOException {
        String url = "http://www.n-park.net/api/common/createOrder?openid=" + orderno + "&orderNo=" + orderno + "&tradeType=JSAPI";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body() == null ? null : response.body().string();
    }

}
