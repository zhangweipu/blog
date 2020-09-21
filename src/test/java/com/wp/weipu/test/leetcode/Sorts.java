package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sorts {

    public int[] mergeSort(int[] arr, int left, int right, int[] tmp) {

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, tmp);
            mergeSort(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
        return arr;
    }

    public void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        //临时数组指针
        int t = 0;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[t++] = arr[i];
                i++;
            } else {
                tmp[t++] = arr[j];
                j++;
            }
        }
        //将剩余左边插入数组
        while (i <= mid) {
            tmp[t++] = arr[i];
            i++;
        }
        //如果是剩余右半面
        while (j <= right) {
            tmp[t++] = arr[j];
            j++;
        }
        t = 0;
        while (left <= right) {
            arr[left++] = tmp[t++];
        }
    }

    public String reverseWords(String s) {
        s = s.trim();
        int i = s.length() - 1, j = i;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j = i;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            sb.append(s.substring(i + 1, j + 1) + " ");
        }
        return sb.toString().trim();
    }

    public String reverseWords2(String s) {
        Pattern m = Pattern.compile(".*?\\s+.*?");
        Matcher s1 = m.matcher(s);
        boolean a = s1.find();
        System.out.println(a);
        String ll = s1.group();
        System.out.println(ll);
        return null;
    }

    @Test
    public void test3() {
        reverseWords("aaa sss");
    }
}
