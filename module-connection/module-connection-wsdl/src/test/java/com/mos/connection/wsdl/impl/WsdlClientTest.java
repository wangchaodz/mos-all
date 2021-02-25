package com.mos.connection.wsdl.impl;

import com.alibaba.fastjson.JSONObject;
import com.mos.connection.wsdl.dto.WsdlRequest;
import com.mos.connection.wsdl.dto.WsdlResponse;
import org.apache.axiom.om.OMElement;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WsdlClientTest {

    @Test
    public void execute() {
        for (int i=0;i<1;i++){
            getSupportCity();
        }
    }

    @Test
    public void getSupportCity(){
        Map<String,String> params = new HashMap<>();
        params.put("byProvinceName","");

        //http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl
        WsdlRequest request = new WsdlRequest();
        request.setNameSpaceUri("http://WebXml.com.cn/");
        request.setServiceUri("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx");
        request.setAction("http://WebXml.com.cn/getSupportCity");
        request.setMethodName("getSupportCity");
        request.setRetries(0);
        request.setParams(params);


        WsdlClient client = new WsdlClient();
        WsdlResponse<OMElement> response = client.execute(request);
        System.out.println("==================================>"+ response.getT());
    }

    @Test
    public void getYanBaoCard(){
        Map<String,String> params = new HashMap<>();
        params.put("arg0","aaaaa");

        //http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl
        WsdlRequest request = new WsdlRequest();
        request.setNameSpaceUri("http://v20.api.igs.einv.ruihong.com/");
        request.setServiceUri("http://172.16.180.4:8088/mockV20FacadeImplServiceSoapBinding");
        request.setAction("");
        request.setMethodName("ch");
        request.setRetries(0);
        request.setParams(params);



        WsdlClient client = new WsdlClient();
        WsdlResponse<OMElement> response = client.execute(request);
        System.out.println("==================================>"+ response.getT());
    }
}