package com.wp.weipu.test.leetcode;

import java.util.Stack;

public class ZJ1 {
    public int getNum() {
        int lang = 1000;
        int n = 0;
        int i = 0;
        while (i < 5) {
            n += (lang + lang / 2);
            lang = lang / 2;
            i++;
        }
        return n;
    }

    public static void main(String[] args) {
        double lang = 1000;
        double n = 0;
        int i = 0;
        while (i < 5) {
            n += (lang + lang / 2.0);
            lang = lang / 2.0;
            i++;
        }
        System.out.println(n);
    }

    /**
     * 建立图
     *
     * @param n
     * @param mat
     */
    public void cycle(int n, int[][] mat) {

    }

    /**
     * 通过单调栈获取最近的满足条件的集合
     *
     * @return
     */
    public int getMax(int[] arr) {
        //单调增栈
        int n = arr.length;
        int[] asc = new int[n];
        //还要有个存索引的
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> index = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && stack.peek() > arr[i]) {
                stack.pop();
                index.pop();
            }
            asc[i] = index.empty() ? 0 : index.peek();
            stack.push(arr[i]);
            index.push(i + 1);
        }

        //单调减栈
        stack.clear();
        index.clear();
        int[] desc = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.empty() && stack.peek() > arr[i]) {
                stack.pop();
                index.pop();
            }
            desc[i] = index.empty() ? 0 : index.peek();
            stack.push(arr[i]);
            index.push(i + 1);
        }
        return 0;
    }
}
