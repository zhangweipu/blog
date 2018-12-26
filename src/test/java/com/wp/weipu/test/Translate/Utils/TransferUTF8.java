package com.wp.weipu.test.Translate.Utils;

import java.io.UnsupportedEncodingException;

/**
 * 需要翻译的文本转换为UTF-8编码
 */
public class TransferUTF8 {

    private static String transferUTF8(String str){
        try {
            return new String(str.getBytes(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
