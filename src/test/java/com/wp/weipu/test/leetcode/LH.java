package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.regex.Pattern;

public class LH {
    @Test
    public void test() {
        isPalindrome("aa.. ,, :: ;..edd 55");
    }

    public boolean isPalindrome(String str) {
        // write code here

        str = str.toLowerCase();
        Pattern p = Pattern.compile("[W|D]+");
        str=str.replaceAll("\\W+","");
        System.out.println(str);
        return true;

    }
}
