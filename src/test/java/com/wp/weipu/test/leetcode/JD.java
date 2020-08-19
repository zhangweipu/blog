package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class JD {

    @Test
    public void test() {
        //保留小数
        BigDecimal bg = new BigDecimal(-1.2333633);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(4);
        System.out.println(nf.format(bg));
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
//        AtomicInteger sum= new AtomicInteger();
        List<Integer> dd = new ArrayList<>();
        list.forEach(x -> {
//            sum.addAndGet(x);
            dd.add(x);
            System.out.println(x);
        });
        System.out.println(JSON.toJSONString(dd));
        System.out.println(new String() == null);
    }

    public String getMaxSubStr(String s1, String s2) {
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        int len1 = a1.length, len2 = a2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int max = 0, maxI = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (a1[i - 1] == a2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        maxI = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = maxI - max; i < maxI; i++) {
            sb.append(a1[i]);
        }
        return sb.toString();
    }

    /**
     * 减速问题
     *
     * @param mat
     */
    public void get(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + mat[i - 1][j - 1], dp[i][j - 1] + mat[i - 1][j - 1]);
            }
        }
        System.out.println(dp[m][n]);
    }

    /**
     * 组合总数
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Stack<Integer> nums = new Stack<>();
        recuNum(candidates, 0, target, 0, nums, list);
        return list;
    }

    public void recuNum(int[] candidates, int index, int target, int sum, Stack<Integer> nums, List<List<Integer>> list) {
        //两个出口
        if (sum == target) {
            list.add(new ArrayList<>(nums));
            return;
        }
        if (sum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] <= target) {
                //错在这个地方了
                nums.push(candidates[i]);
                recuNum(candidates, i, target, sum + candidates[i], nums, list);
                nums.pop();
            }

        }
    }

    @Test
    public void test5() {
        int[] candidates = new int[]{2, 3, 6, 7};
        List<List<Integer>> res = combinationSum(candidates, 7);
        System.out.println(JSON.toJSONString(res));
    }

    /**
     * 异或去重 加减法去重
     */
    @Test
    public void test3() {
        String a = "aaabbbcc";
        String b = "aababbcce";
        int res = 0;
        System.out.println('a' ^ 'b' ^ 'a');
        for (char c : a.toCharArray()) {
            res ^= c;
        }
        System.out.println(res);
        for (char c : b.toCharArray()) {
            res ^= c;
        }
        System.out.println(res);
    }

    /**
     * 递归要设置出口，从后往前遍历
     *
     * @param arr
     * @param low
     * @param high
     */
    public void quickSort(int[] arr, int low, int high) {
//        int mid = (end + start) / 2;
        if (low >= high) {
            return;
        }
        int i = low, j = high;
        int tmp = arr[low];
        //快排是从队尾开始移动的
        while (low < high) {
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = tmp;
        quickSort(arr, i, low);
        quickSort(arr, low + 1, j);
    }

    @Test
    public void test6() {
        int[] arr = new int[]{1, 2, 3, 6, 5, 9, 4, 11, 22, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));
    }

    public void getNext(String p, int[] next) {
        int i = 0, j = -1;
        next[0] = -1;
        while (i < p.length()) {
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                //回溯到之前的位置
                j = next[i];
            }
        }
    }

    public int match(String s, String p, int[] next) {
        if (s == null || p == null) {
            return -1;
        }
        int sLen = s.length();
        int pLen = p.length();
        if (sLen < pLen) {
            return -1;
        }
        int i = 0, j = 0;
        while (i < sLen && j < pLen) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[i];
            }
            if (j >= pLen) {
                //匹配成功
                return i - pLen;
            }
            return -1;
        }
        return -1;
    }

    /**
     * 我应该还是选择递归
     */
    @Test
    public void test7() {
        int n = 29;
        int[] arr = new int[]{2, 3, 5, 7};
        for (int m : arr) {
            if (n == m) {
                System.out.println(0);
                return;
            }
        }
        int[] dp = new int[arr.length];
        System.out.println(getNum(arr, 0, dp, n));

    }

    public int getNum(int[] arr, int index, int[] colored, int num) {
        if (num == 1) {
            return 1;
        }
        int n = 0;
        for (int i = index; i < arr.length; i++) {
            if (colored[i] == 0) {
                if (num % arr[i] == 0) {
                    n += getNum(arr, i, colored, num / arr[i]);
                }
            }
        }
        if (n == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * 插入排序
     * 不能计算最小插入次数
     */
    public void sort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j] > a[j - 1]; j--) {
                int tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
            }
        }
    }

    /**
     * 这样不好啊
     * 使用链表比较方便
     *
     * @param arr
     */
    public void chooseSort(int[] arr) {
        int tmp = 0, j = 0;
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int tmp1 = arr[i - 1];
                j = i + 1;
                while (j < arr.length && tmp1 > arr[j]) {
                    arr[j - 1] = arr[j];
                    j++;
                }
                if (j != i + 1) {
                    arr[j] = tmp;
                    count++;
                }
            }
        }
    }

    class node {
        int data;
        node next;

        public node(int data) {
            this.data = data;
        }
    }

    @Test
    public void test8() {
        int[] arr = new int[]{1, 3, 4, 5, 6};
        node head = new node(-1), tmp;
        tmp = head;
        for (int i = 0; i < arr.length; i++) {

        }
    }

    /**
     * 构造最小回文串
     * 使用堆
     */
    public void getHuiWen(String str) {
        Stack<Character> stack = new Stack<>();
        int n = str.length();
        stack.push(str.charAt(0));
        int i = 0;
        boolean flag = true;
        //判断出栈完成的时机
        for (i = 1; i < n; i++) {

        }

    }
}
