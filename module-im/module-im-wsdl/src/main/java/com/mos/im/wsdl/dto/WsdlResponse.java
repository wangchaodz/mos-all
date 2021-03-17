package com.mos.connection.wsdl.dto;

import java.io.Serializable;

/**
 * 请用一句话描述下你打算干啥
 * @date  2021/1/30 15:40
 * @author  wangchaodz@gmail.com
 **/
public class WsdlResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T t;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
