package com.wp.weipu.test;

import org.hibernate.validator.constraints.SafeHtml;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zwp
 */

public class StrTest {

    @Test
    public void srt() throws UnsupportedEncodingException {
        String jsonStr="aaaa中国11{,-A\"";
        String str11=jsonStr;
        //判断字符串编码
        String encoding2 = "GBK";
        if (jsonStr.equals(new String(jsonStr.getBytes(encoding2),encoding2))){
            System.out.println("编码为：GBK");
        }
        String encoding1 = "UTF-8";
        if (jsonStr.equals(new String(jsonStr.getBytes(encoding1),encoding1))){
            System.out.println("编码为：UTF-8");
        }

        String encoding3 = "GB2312";
        if (jsonStr.equals(new String(jsonStr.getBytes(encoding3),encoding3))){
            System.out.println("编码为：GB2312");
        }
        String encoding4 = "ISO-8859-1";
        if (jsonStr.equals(new String(jsonStr.getBytes(encoding4),encoding4))){
            System.out.println("编码为：ISO-8859-1");
        }

        System.out.println("jsostr"+jsonStr.getBytes());
        System.out.println("str11"+str11.getBytes());

        System.out.println("中文");

        System.out.println("utf-8中文".getBytes("UTF-8"));

        System.out.println(new String("gb2312中文".getBytes("GB2312")));

        System.out.println("iao中文".getBytes("ISO8859_1"));

        System.out.println(new String("1231122中文".getBytes(),"UTF-8"));

        System.out.println(new String("中文".getBytes(), "GB2312"));

        System.out.println(new String("中文".getBytes(), "ISO8859_1"));

        System.out.println(new String("中文".getBytes("GB2312")));

        System.out.println(new String("gb{-;'中文".getBytes("GB2312"), "GB2312"));

        System.out.println(new String("中文".getBytes("GB2312"), "ISO8859_1"));

        System.out.println(new String("中文".getBytes("ISO8859_1")));

        System.out.println(new String("中文".getBytes("ISO8859_1"), "GB2312"));

        System.out.println(new String("中文".getBytes("ISO8859_1"), "ISO8859_1"));
    }

    @Test
    public void tetstss(){
        List<Demo> list=new ArrayList<>();
        Map<String,List<Demo>> map=new HashMap<>();
        Demo d=new Demo();
        ConverToMapTest.convertMap("aa",d,map);
        System.out.println(map);
        System.out.println("㎡".length());
    }
}
