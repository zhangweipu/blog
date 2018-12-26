package com.wp.weipu.test;

import org.junit.Test;

import java.util.StringTokenizer;

public class StrSplit {


    /**
     * 分割字符串
     */
    @Test
    public void test1() {
        String ip = "127.0.0.1";
        StringTokenizer stringTokenizer = new StringTokenizer(ip, ".");
        while (stringTokenizer.hasMoreElements()) {
            System.out.println(stringTokenizer.nextElement());
        }
    }

    @Test
    public void test2(){

    }
}
