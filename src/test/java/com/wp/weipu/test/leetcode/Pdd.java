package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.List;
import java.util.Locale;

public class Pdd {
    /**
     * 计算两个字符串的和，字符串是由0-9组成的
     * 从最后开始加
     *
     * @param num1
     * @param num2
     * @return
     */
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
        String sub = len1 > 0 ? num1 : num2;
        int sunlen = len1 > 0 ? len1 : len2;
        if (val == 0) {
            sb.insert(0, sub.substring(0, sunlen + 1));
            return sb.toString();
        }
        while (sunlen > 0) {
            int a = sub.charAt(len1) - '0';
            int sum = a + val;
            if (sum >= 9) {
                sb.insert(0, sum % 10);
                val = 1;
            } else {
                sb.insert(0, sum);
                val = 0;
            }
            sunlen--;
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String a = addStrings("5555", "11112");
        System.out.println(a);
    }

    /**
     * 换成奇数就不行了。。。 不对
     *
     * @param mat
     * @param n
     * @return
     */
    public int[][] getRegin(int[][] mat, int n) {
        for (int i = 0; i < n; i++) {
            mat[i][i] = -1;
            mat[i][n - 1 - i] = -1;
        }
        int mid = 0;
        if (n % 2 == 1) {
            mid = n / 2 + 1;
            for (int i = 0; i < n; i++) {
                mat[i][mid] = -1;
                mat[mid][i] = -1;
            }
        }


        mid = n / 2 + 1;
        //1
        colored(mat, 0, mid, mid, n - 1, 1);
        //2
        colored(mat, 0, mid - 1, 0, mid, 3);
        colored(mat, mid, n - 1, 0, mid, 4);
        colored(mat, mid, n - 1, mid, n - 1, 6);
        colored2(mat, 0, mid - 1, 0, mid - 1, 2);
        colored2(mat, mid, n - 1, 0, mid - 1, 5);
        colored2(mat, mid, n - 1, mid, n - 1, 7);
        colored2(mat, 0, mid - 1, mid, n - 1, 8);

        return mat;
    }

    public void colored(int[][] mat, int istart, int iend, int jstart, int jend, int num) {
        for (int i = istart; i <= iend; i++) {
            int j = jstart;
            while (j <= jend) {
                if (mat[i][j] == -1) {
                    break;
                }
                mat[i][j] = num;
                j++;
            }
        }
    }

    public void colored2(int[][] mat, int istart, int iend, int jstart, int jend, int num) {
        for (int i = istart; i <= iend; i++) {
            int j = jend;
            while (j >= jstart) {

                if (mat[i][j] == -1) {
                    break;
                }
                mat[i][j] = num;
                j--;
            }
        }
    }

    @Test
    public void test5() {
        int[][] mat = new int[9][9];
        getRegin(mat, 9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     * 判断是否为数字，使用正则表达式试试
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        //标记状态
        boolean isNum = false, isSign = false, iseOrE = false;
        char[] arr = s.toCharArray();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
        }
        return true;
    }

    @Test
    public void test7() {
        String p = ".*?[^e]";
        String aa = "aabbnn";
        System.out.println(aa.matches(p));
        System.out.format(Locale.CHINESE,"sssss");
    }
}
