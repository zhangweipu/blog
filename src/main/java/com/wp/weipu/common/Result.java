package com.wp.weipu.common;

import java.io.Serializable;

/**
 * @author zwp
 */

public class Result<T> implements Serializable{
    private static final long serialVersionUID = -1L;
    private String success;
    private String msg;
    T data;

    public Result(T data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
