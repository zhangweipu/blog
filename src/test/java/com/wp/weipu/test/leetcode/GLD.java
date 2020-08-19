package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * 广联达的笔试
 */
public class GLD {

    public int dance(String a, String b) {
        int len = a.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sum += 20;
            } else {
                sum = Math.max(sum - 10, 0);
            }
        }
        return sum;
    }

    @Test
    public void test1() {
        System.out.println(dance("WASDWWSAD", "WASSWWAAD"));
    }

    /**
     * 是所有元素相等
     * 肯定是平衡最大和最小的了
     * 怎么确定最矮，最优的那个
     * 选出最小的，然后算平均
     * 以上都不行
     * 采用循环的方法，每次先排序，最大的补贴最小的
     *
     * @param arr
     * @return
     */
    public int balance(int[] arr) {
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        while (arr[i] != arr[j]) {
            arr[i] += 1;
            arr[j] -= 2;
            if (arr[j] < arr[i]) {
                return -1;
            }
            Arrays.sort(arr);
            if (arr[i] == arr[j]) {
                break;
            }
        }
        return Arrays.stream(arr).sum();
    }

    @Test
    public void test2() {
        int[] arr = new int[]{2, 2, 2, 101};
        System.out.println(balance(arr));
    }

    /**
     * 回溯算法吧
     *
     * @param arr
     * @return
     */
    public int happy(int[][] arr, int sum, int[] color, int power) {
        if (power < 0) {
            return 0;
        }
        if (power == 0) {
            return sum;
        }
        int max = sum;
        for (int i = 0; i < color.length; i++) {
            if (color[i] == 0 && power >= arr[i][0]) {
                color[i] = 1;
                max = Math.max(sum, happy(arr, sum + arr[i][1], color, power - arr[i][0]));
                color[i] = 0;
            }
        }
        return max;
    }

    @Test
    public void test3() {
        int[][] arr = new int[][]{{5, 16}, {9, 1}, {8, 18}};
        int[] color = new int[3];
        System.out.println(happy(arr, 0, color, 15));
    }
}
