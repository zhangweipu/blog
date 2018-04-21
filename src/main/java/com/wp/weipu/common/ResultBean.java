package com.wp.weipu.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.List;

/**
 * @author zwp
 */

public class ResultBean<T> implements Serializable{
    public static final int FAIL = 0;
    public static final int SUCCESS = 1;
    public static final int TOKEN = 2;
    public static final int OTHER = 9;
    private static final long serialVersionUID = -8528102467884030980L;
    private String msg = "success";
    private int code = 1;
    private T data;

    public ResultBean() {
    }

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(Throwable e) {
        this.msg=e.toString();
        code=0;
    }

    public <E> E converDataEntity(Class<E> clazz) {
        return JSON.parseObject(JSON.toJSONString(this.data, new SerializerFeature[]{SerializerFeature.WriteNonStringValueAsString}), clazz);
    }

    public JSONObject converDataJSONObject() {
        return JSON.parseObject(JSON.toJSONString(this.data, new SerializerFeature[]{SerializerFeature.WriteNonStringValueAsString}));
    }

    public <E> List<E> converDataArray(Class<E> clazz) {
        return JSON.parseArray(JSON.toJSONString(this.data, new SerializerFeature[]{SerializerFeature.WriteNonStringValueAsString}), clazz);
    }

    public JSONArray converDataJSONArray() {
        return JSON.parseArray(JSON.toJSONString(this.data, new SerializerFeature[]{SerializerFeature.WriteNonStringValueAsString}));
    }

    public static int getFAIL() {
        return FAIL;
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getTOKEN() {
        return TOKEN;
    }

    public static int getOTHER() {
        return OTHER;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
