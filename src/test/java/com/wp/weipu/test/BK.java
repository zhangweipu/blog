package com.wp.weipu.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class BK {

    public static int getHuiwen(String str1) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        char[] str = str1.toCharArray();
//StringBuilder sb=new StringBuilder("");
//sb.append()
        int len = str.length;
        int i = 0, j = len - 1;
        int count = 0;
        while (i < j) {
            if (str[i] != str[j]) {
                count++;
            }
            i++;
            j--;
        }
        return count;
    }

    @Test
    public void test() {
        String a = "acacttteekkb";
        System.out.println(getHuiwen(a));
    }

    public static int getMod(int[] mat) {
        int a = mat[2], b = mat[3];
        return 0;
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 2, 4, 4};
        int max = 0;
        int maxave = 0;
        //排序 最后两个的值肯定最大,用平均值比较大小
        Arrays.sort(nums);
        int maxlen = 0;
        int len = 1;
        for (int n = nums.length - 1; n >= 0; n--) {
            int m = max | nums[n];
            int ave = m / len;
            if (maxave <= ave && m > max) {
                maxlen = len;
                maxave = ave;
                max = m;
            }
            len++;
        }
        System.out.println(maxlen);
    }
}
