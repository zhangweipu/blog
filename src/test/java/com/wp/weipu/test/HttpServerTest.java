package com.wp.weipu.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * @author zwp
 */

public class HttpServerTest {

    public void httpServer(String url) throws IOException {
        //创建一个类似浏览器的客户端
        HttpClient httpClient=new HttpClient();
        //创建get方法类似输入地址
        GetMethod getMethod=new GetMethod(url);
        //回车，获取相应状态码
        int statusCode=httpClient.executeMethod(getMethod);

    }
}
