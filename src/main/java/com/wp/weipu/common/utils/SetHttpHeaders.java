package com.wp.weipu.common.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置一些请求头部，或者相关
 */
public class SetHttpHeaders {
    public static HttpServletResponse setHeaders(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("设置header");
        return response;
    }
}
