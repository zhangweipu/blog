package com.wp.weipu.test.translate.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 在发送HTTP请求之前需要对各字段做URL encode。
 */
public class UrlEncode {
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
