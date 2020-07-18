package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.List;

/**
 * leetcode中简单的题
 */
public class Simpleclass {
    /**
     * 二进制运算
     * 输入: a = "11", b = "1"
     * 输出: "100"
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int len = Math.min(aLen, bLen);
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        //进位
        char tmp = '0';
        //怎么进位
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= len; i++) {
            //想等的情况
            if (aChar[aLen - i] == bChar[bLen - i]) {
                if (tmp == '0') {
                    sb.append('0');
                    if (aChar[aLen - i] == '1') {
                        tmp = '1';
                    }
                } else if (aChar[aLen - i] == '0') {
                    sb.append('1');
                    tmp = '0';
                } else {
                    sb.append('1');
                    tmp = '1';
                }
            } else {
                if (tmp == '0') {
                    sb.append('1');
                } else {
                    sb.append('0');
                    tmp = '1';
                }
            }

        }
        char[] aa = len == aLen ? bChar : aChar;
        for (int i = aa.length - 1 - len; i >= 0; i--) {
            if (aa[i] == tmp) {
                sb.append('0');
            } else {
                sb.append('1');
                tmp = '0';
            }
        }
        if (tmp == '1') {
            sb.append(tmp);
        }
        sb.reverse();
        return sb.toString();
    }

    @Test
    public void test1() {
        String res = addBinary("101", "10");
        System.out.println(res);
    }

    /**
     * 明天在刷
     * 排序 先固定俩，再双指针
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }

    /**
     * 外观数列
     * 对上一个数的描述
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {

        if (n == 2) {
            return "11";
        }
        StringBuilder sb = new StringBuilder("11");
        //1另算
        for (int i = 0; i < n-2; i++) {
            int len = sb.length();
            int index = 0;
            StringBuilder tmp = new StringBuilder();
            while (index < len) {
                int num = 1;
                while (index < len - 1 && sb.charAt(index) == sb.charAt(index + 1)) {
                    num++;
                    index++;
                }
                tmp.append(num).append(sb.charAt(index));
                index++;
            }
            sb = tmp;
        }
        return sb.toString();
    }

    @Test
    public void test2() {
        String res = countAndSay(4);
        System.out.println(res);
    }
}
