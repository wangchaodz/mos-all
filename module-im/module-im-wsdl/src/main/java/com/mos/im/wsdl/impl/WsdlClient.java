package com.mos.connection.wsdl.impl;

import com.mos.connection.RunClient;
import com.mos.connection.wsdl.dto.WsdlRequest;
import com.mos.connection.wsdl.dto.WsdlResponse;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.util.Map;

/**
 * RPC实现调用WS服务
 *
 * @author wangchaodz@gmail.com
 * @date 2021/1/29 11:02
 **/
public class WsdlClient implements RunClient<WsdlRequest, WsdlResponse<OMElement>> {

    @Override
    public WsdlResponse<OMElement> execute(WsdlRequest request) {
        WsdlResponse<OMElement> response = new WsdlResponse<>();
        response.setCode(0);
        response.setMsg("未处理");
        try {
            //拼接请求参数
            OMFactory fac = OMAbstractFactory.getOMFactory();
            OMNamespace omNs = fac.createOMNamespace(request.getNameSpaceUri(), request.getPrefix());
            OMElement requestData = fac.createOMElement(request.getMethodName(), omNs);
            if (request.getParams() != null && request.getParams().size() > 0) {
                for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
                    OMElement arg = fac.createOMElement(entry.getKey(), omNs);
                    arg.setText(entry.getValue());
                    requestData.addChild(arg);
                }
            }

            //配置连接参数
            Options options = new Options();
            //调用地址
            options.setTo(new EndpointReference(request.getServiceUri()));
            options.setAction(request.getAction());
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            //设置不受限制.
            options.setProperty(HTTPConstants.CHUNKED, "false");
            HttpMethodParams methodParams = new HttpMethodParams();
            DefaultHttpMethodRetryHandler retryHandler;
            if (request.getRetries() <= 0) {
                retryHandler = new DefaultHttpMethodRetryHandler(0, false);
            } else {
                retryHandler = new DefaultHttpMethodRetryHandler(request.getRetries(), true);
            }
            methodParams.setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);
            options.setProperty(HTTPConstants.HTTP_METHOD_PARAMS, methodParams);

            if (request.getHeaders() != null && request.getHeaders().size() > 0) {
                for (Map.Entry<String, Object> entry : request.getHeaders().entrySet()) {
                    options.setProperty(entry.getKey(), entry.getValue());
                }
            }

            // 调用WebService方法 传递参数，调用服务，获取服务返回结果集
            ServiceClient serviceClient = new ServiceClient();
            serviceClient.setOptions(options);
            OMElement omElement = serviceClient.sendReceive(requestData);
            if (omElement == null) {
                response.setCode(1);
                response.setMsg("调用失败");
            } else {
                response.setCode(2);
                response.setMsg("调用成功");
                response.setT(omElement);
            }
        } catch (Exception e) {
            response.setCode(1);
            response.setMsg("调用失败");
            e.printStackTrace();
        }
        return response;
    }
}
