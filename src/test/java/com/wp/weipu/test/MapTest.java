package com.wp.weipu.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zwp
 */

public class MapTest {

    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        if (null == map.get("1")) {
            System.out.printf("可以");
        }
    }

    @Test
    public void test() {
        String a = addStrings("99", "9");
        System.out.println(a);
    }

    public String addStrings(String num1, String num2) {
        int len1 = num1.length() - 1, len2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder("");
        int minLen = Math.min(len1, len2);
        //声明进位
        int val = 0;
        while (len1 >= 0 && len2 >= 0) {
            //求他们的整数大小
            int a = num1.charAt(len1) - '0';
            int b = num2.charAt(len2) - '0';
            int sum = a + b + val;
            if (sum > 9) {
                sb.insert(0, sum % 10);
                val = 1;
            } else {
                sb.insert(0, sum);
                val = 0;
            }
            len1--;
            len2--;
        }
        //这个地方有问题啊
        String sub = len1 >= 0 ? num1 : num2;
        int sunlen = len1 >= 0 ? len1 : len2;
        if (val == 0) {
            sb.insert(0, sub.substring(0, sunlen + 1));
            return sb.toString();
        }
        while (sunlen >= 0) {
            int a = sub.charAt(sunlen) - '0';
            int sum = a + val;
            if (sum > 9) {
                sb.insert(0, sum % 10);
                val = 1;
            } else {
                sb.insert(0, sum);
                val = 0;
            }
            sunlen--;
        }
        if (val == 1) {
            sb.insert(0, "1");
        }
        return sb.toString();
    }

    public String addString2(String num1, String num2) {
        StringBuilder sb = new StringBuilder("");
        int len1 = num1.length() - 1, len2 = num2.length() - 1;
        int carry = 0;
        while (len1 >= 0 || len2 >= 0 || carry != 0) {
            int a = len1 < 0 ? 0 : num1.charAt(len1--) - '0';
            int b = len2 < 0 ? 0 : num2.charAt(len2--) - '0';
            int sum = a + b + carry;
            sb.insert(0, sum % 10);
            carry = carry / 10;
        }
        return sb.toString();
    }
}
