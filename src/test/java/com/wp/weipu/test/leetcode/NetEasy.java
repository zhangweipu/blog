package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class NetEasy {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    /**
     * 拆数
     *
     * @param num
     * @return
     */
    public int splitNum(int num) {
        if (num == 1) {
            return 0;
        }
        int sub = num - 2;
        int sum = 0;
        while (sub > 2) {
            sum++;
            sub -= 2;
        }
        if (isSu(sub)) {
            sum++;
        }
        return sum;
    }

    public boolean isSu(int num) {

        int i = 2;
        while (i * i <= num) {
            int j = num / i;
            if (i * j == num) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Test
    public void test() {
        int[] a = {7, 5, 39};
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += splitNum(a[i]);
        }
        System.out.println(sum);
        if (isSu(0)) {
            System.out.println("shi");
        }
    }

    public static int getNum(int[] a) {
        Arrays.sort(a);
        int i = a.length - 1;
        int sum1 = a[i--], sum2 = 0;
        int max = 0;
        int sum = sum1;
        while (i >= 0) {
            sum += a[i];
            if (sum1 > sum2) {
                sum2 += a[i--];
            } else {
                sum1 += a[i--];
            }
            if (sum1 == sum2) {
                max = 2 * sum1;
            }
        }

        return sum - max;
    }

    @Test
    public void test1() {
        int[] a = {5, 15, 30, 30, 60};
        int res = getNum(a);
        System.out.println(res);
    }

    public static String getBetween(String a, String b) {
        int aLen = a.length(), bLen = b.length();
        int len = Math.min(aLen, bLen);
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sb.append(a.charAt(i));
            } else if (a.charAt(i) + 1 < b.charAt(i)) {
                sb.append((char)(a.charAt(i) + 1));
            } else if (a.charAt(i) + 1 == b.charAt(i)) {
                sb.append(b.charAt(i));
                if (i < b.length() - 1) {
                    sb.append((char)(b.charAt(i + 1) - 1));
                }
            }
        }
        return sb.toString();
    }

    @Test
    public void test3() {
        String res = getBetween("aac", "abcb");
        System.out.println(res);
        System.out.println((char) ('a' + 1));


    }
}
