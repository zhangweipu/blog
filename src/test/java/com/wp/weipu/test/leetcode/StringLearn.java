package com.wp.weipu.test.leetcode;

import org.junit.Test;

import javax.swing.*;
import java.util.*;

/**
 * 字符串相关练习
 */
public class StringLearn {
    /**
     * 求字符串的全排列
     *
     * @param str
     * @param start
     */
    public void permutation(char[] str, int start) {
        if (str == null || start < 0) {
            return;
        }
        if (start == str.length - 1) {
            //这里是调整顺序到了最后一位
            System.out.println(str);
            return;
        }

        for (int i = start; i < str.length; i++) {
            //调整的目的是保证每个字符的位置都得到调整
            swap(str, start, i);
            //从下一个字符开始
            permutation(str, start + 1);
            //复原原来的字符串
            swap(str, i, start);
        }
    }

    /**
     * 交换位置
     *
     * @param str
     * @param i
     * @param j
     */
    public void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    @Test
    public void test() {
        String str = "abc";
        char[] arr = str.toCharArray();
        permutation(arr, 0);
    }

    /**
     * 最长公共子串
     * 通过一个数组，记录
     *
     * @return
     */
    public void maxCommonSubstr(char[] str1, char[] str2) {
        int len1 = str1.length;
        int len2 = str2.length;
        //[0][0]的时候不做比较
        int[][] len = new int[len1 + 1][len2 + 1];
        int max = 0;
        int index = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    len[i][j] = len[i - 1][j - 1] + 1;
                    if (len[i][j] > max) {
                        max = len[i][j];
                        index = i;
                    }
                } else {
                    len[i][j] = 0;
                }
            }
        }

        for (int i = index - max; i < index; i++) {
            System.out.println(str1[i]);
        }
    }

    @Test
    public void test2() {
        String a = "abccade";
        String b = "dgcadde";
//        maxCommonSubstr(a.toCharArray(), b.toCharArray());
        strReverse(a);
    }

    public void strReverse(String str) {
        char[] s = str.toCharArray();
        int len = s.length;
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
        for (int i = 0; i < len; i++) {
            System.out.println(s[i]);
        }
    }

    public void compare(String a, String b) {
        int res = 0;
        for (char m : a.toCharArray()) {
            res ^= m;
        }
        for (char n : b.toCharArray()) {
            res ^= n;
        }
        System.out.println(res);
    }

    @Test
    public void test3() {
        compare("aaabb", "bbaaa");
    }

    /**
     * 判断包含问题
     * 空间换时间，设置一个数组，256的数组
     *
     * @param a
     * @param b
     * @return
     */
    public boolean contain(String a, String b) {
        int lena = a.length();
        int lenb = b.length();
        //还要用到计数所以放在外面
        int i, j;
        if (lena < lenb) {
            for (i = 0; i < lena; i++) {
                for (j = 0; j < lenb; j++) {
                    if (a.charAt(i) == b.charAt(j)) {
                        break;
                    }
                }
                //等于表示不是通过break跳出的
                if (j >= lenb) {
                    return false;
                }
            }
        } else {
            for (j = 0; j < lenb; j++) {
                for (i = 0; i < lena; i++) {
                    if (a.charAt(i) == b.charAt(j)) {
                        break;
                    }
                }
                if (i >= lena) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test5() {
        if (contain("aaaa", "an")) {
            System.out.println("zai");
        }
    }

    /**
     * 把字符串分开
     *
     * @param ch
     */
    public void sortArr(char[] ch) {
        int i = 0, j = ch.length - 1;
        while (i < j) {
            while (i < j && ch[i] <= 'Z') {
                i++;
            }
            while (i < j && ch[j] > 'Z') {
                j--;
            }
            char tmp = ch[i];
            ch[i] = ch[j];
            ch[j] = tmp;
        }
        for (char c : ch) {
            System.out.println(c);
        }
    }

    @Test
    public void test6() {
        String ch = "AasssSdsffFFHHdd";
        sortArr(ch.toCharArray());
        if ('A' < 'z') {
            System.out.println("ss");
        }
    }

    /**
     * 首先是括号匹配，其次是保存非括号字符串
     *
     * @param cha
     */
    public void removeNestedPare(String cha) {
        int count = 0;
        StringBuffer str = new StringBuffer();
        for (char c : cha.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            } else {
                str.append(c);
            }
        }
        if (count != 0) {
            System.out.println("格式不对");
        }
        System.out.println(str);
    }

    @Test
    public void test7() {
        removeNestedPare("((a,s,(s,s))");
    }

    /**
     * 判断字符串是否为整数
     * 要的肯定不是这种算法
     *
     * @return
     */
    public int isNumber(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println("不是数字");
        }
        return 0;
    }

    public boolean isNumber(char c) {
//        if (c >= '0' && c <= '9') {
//            return true;
//        } else {
//            return false;
//        }
        // 写那么多废话干啥
        return c >= '0' && c <= '9';
    }

    /**
     * 递归构造一个整数
     *
     * @return
     */
    public int isNumber1(String str) {
        int i = 0, j = str.length() - 1;
        int symbol = 1;
        if (str.charAt(0) == '-') {
            symbol = -1;
            i++;
            j--;
        } else if (str.charAt(0) == '+') {
            symbol = 1;
            i++;
            j--;
        }
        int sum = 0;
        for (int m = i; m < str.length(); m++) {
            if (str.charAt(m) >= '0' && str.charAt(m) <= '9') {
                //直接每次乘以10 就好了。。。。。。
                int b = (int) Math.pow(10, j);
                sum += b * (str.charAt(m) - '0');
                j--;
            } else {
                return -1;
            }
        }
        return sum * symbol;

    }

    @Test
    public void test8() {
        System.out.println(isNumber1("-+1234"));
        System.out.println((int) ('2' - '0'));
    }

    /**
     * 著名的kmp算法
     *
     * @param p
     * @param s
     * @return
     */
    public int match(String p, String s) {
        return 0;
    }

    /**
     * 获得匹配串的next数组
     *
     * @param p
     * @return
     */
    public int[] getNext(String p) {
        int[] next = new int[p.length()];
        int i = 0, j = -1;
        while (i < p.length()) {
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * 使用位运算做标记
     * 各种字符有256个，每个int占32bit 8位等于1字节 8个int就够了
     *
     * @param str
     * @return
     */
    public boolean isDup(String str) {
        int len = str.length();
        int[] flag = new int[8];
        for (int i = 0; i < len; i++) {
            //确定所在数组中的位置，因为每个数占32bit
            int index = (int) str.charAt(i) / 32;
            //在32位中的位置
            int shift = (int) str.charAt(i) % 32;
            //比较该位置是否为0
            if ((flag[index] & (1 << shift)) != 0) {
                return true;
            }
            //把标记放到对应位置
            flag[index] |= (1 << shift);
        }
        return false;
    }

    @Test
    public void test9() {
        isDup("good");
    }

    /**
     * 获取最大重复的字符
     * 使用递归或者动态规划可以的
     *
     * @return
     */
    public int getMaxDup(String str) {
        int max = Integer.MIN_VALUE, count = 1;
        char tmp = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (tmp == str.charAt(i)) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 1;
                tmp = str.charAt(i);
            }
        }
        return max;
    }

    /**
     * 使用递归实现
     *
     * @return
     */
    public int getMaxDup(String str, int i, int max, int count) {
        if (i == str.length() - 1) {
            return Math.max(max, count);
        }
        if (str.charAt(i) == str.charAt(i + 1)) {
            return getMaxDup(str, i + 1, max, count + 1);
        } else {
            return getMaxDup(str, i + 1, max, 1);
        }
    }

    @Test
    public void test10() {
        System.out.println(getMaxDup("aaabhhhhhh"));
    }

    /**
     * 判断str1是不是str2旋转的
     *
     * @param str1
     * @param str2
     */
    public boolean rotateSame(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int n = str1.length();
        //str2的索引
        int j = 0, i = 0;
        while (i < n) {
            int m = i;
            while (i < n && j < n) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    j++;
                    i++;
                } else {
                    j = 0;
                    i = m + 1;
                    break;
                }
            }
            if (j >= n) {
                break;
            }
        }
        if (j < n && i == n) {
            for (i = 0; j < n && i < n; i++, j++) {
                if (str1.charAt(i) != str2.charAt(j)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Test
    public void test11() {
        if (rotateSame("waierbottle", "erbottlewao")) {
            System.out.println("是");
        } else {
            System.out.println("不是");
        }
    }

    /**
     * 获取最短数组和
     *
     * @return
     */
    public int getMinPath(int[][] mat, int i, int j, int count) {
        int col = mat.length;
        int row = mat[0].length;
        if (i == col - 1 && j == row - 1) {
            return count + mat[i][j];
        }
        int max = 0;
        return max;
    }

    /**
     * 书上的代码
     *
     * @return
     */
    public int getMinPath(int[][] arr, int i, int j) {
        if (i == 0 && j == 0) {
            return arr[i][j];
        }
        if (i > 0 && j > 0) {
            return arr[i][j] + Math.min(getMinPath(arr, i - 1, j), getMinPath(arr, i, j - 1));
        } else if (i > 0 && j == 0) {
            return arr[i][j] + getMinPath(arr, i - 1, j);
        } else {
            return arr[i][j] + getMinPath(arr, i, j - 1);
        }
    }

    public int getMinPath(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return getMinPath(arr, arr.length - 1, arr[0].length - 1);
    }

    @Test
    public void test12() {
        int[][] arr = {{1, 50, 50, 50, 50, 50, 50, 50},
                {1, 50, 1, 1, 1, 50, 50, 50},
                {1, 1, 1, 50, 1, 50, 50, 50},
                {50, 50, 50, 50, 1, 50, 50, 50},
                {50, 50, 50, 50, 1, 1, 1, 50},
                {50, 50, 50, 50, 50, 50, 1, 1},
                {50, 50, 50, 50, 50, 50, 50, 1}};
//        System.out.println(getMinPath(arr));


    }

    /**
     * 获取相对路径
     *
     * @param path1
     * @param path2
     * @return
     */
    public String getRelativePath(String path1, String path2) {
        String[] path1s = path1.split("/");
        String[] path2s = path2.split("/");
        int i = 0;
        for (i = 0; i < path1s.length; i++) {
            if (path1s[i] != path2s[i]) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < path2s.length - 1; j++) {
            if (j < i) {
                sb.append("../");
            } else {
                sb.append(path2s[j]).append("/");
            }
        }
        sb.append(path2s[path2s.length - 1]);
        return sb.toString();
    }

    class Item {
        public String word;
        public int len;

        public Item(String word, int len) {
            this.word = word;
            this.len = len;
        }
    }

    /**
     * @param pre
     * @param tail
     * @return
     */
    public boolean isMatch(String pre, String tail) {
        int len = pre.length(), count = 0;
        for (int i = 0; i < len; i++) {
            if (pre.charAt(i) != tail.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }

    /**
     * 最短路径，广度优先遍历。。
     *
     * @return
     */
    public int shortestChain(String start, String target, Set<String> map) {
        if (isMatch(start, target)) {
            return 1;
        }
        Queue<Item> items = new ArrayDeque<>();
        items.add(new Item(start, 1));
        while (!items.isEmpty()) {
            Item curr = items.poll();
            for (String s : map) {

                if (isMatch(curr.word, s)) {
                    //把长度都带着了，，所以相当于记忆了。。。
                    Item push = new Item(s, curr.len + 1);
                    items.add(push);
                    map.remove(s);
                    if (s.equals(target)) {
                        return push.len;
                    }
                }

            }

        }
        return 0;
    }
}
