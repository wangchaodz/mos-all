package com.mos.connection.wsdl.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * 请用一句话描述下你打算干啥
 * @date  2021/1/30 15:40
 * @author  wangchaodz@gmail.com
 **/
public class WsdlRequest implements Serializable {

    private String nameSpaceUri;

    private String prefix = "";

    private String serviceUri;

    private String action;

    private String methodName;

    private int retries = 3;

    private Map<String, Object> headers = null;

    private Map<String, String> params = null;

    private Class<?> returnType;

    public String getNameSpaceUri() {
        return nameSpaceUri;
    }

    public void setNameSpaceUri(String nameSpaceUri) {
        this.nameSpaceUri = nameSpaceUri;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
