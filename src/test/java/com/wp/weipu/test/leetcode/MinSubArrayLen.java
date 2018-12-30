package com.wp.weipu.test.leetcode;

import org.junit.Test;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 * 如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 设置一个区间，区间的长度重1到n
 */
public class MinSubArrayLen {
    /**
     * 效率太差，不能通过
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int win = 1;
        int n = nums.length;
        while (win <= n) {
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < i + win && j < n; j++) {
                    sum += nums[j];
                }
                if (sum >= s) {
                    return win;
                }
            }
            win++;
        }
        return 0;
    }

    /**
     * 做差 先排个序
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int s, int[] nums) {
        int n = nums.length;
        int m = s;
        sort(nums, 0, n - 1);
        int index = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > m || nums[i] == m) {
                return index;
            } else {
                m = m - nums[i];
                index++;
            }
        }
        return 0;
    }

    /**
     * 从大到小排序先
     *
     * @return
     */
    public void sort(int[] nums, int low, int height) {

        int i = low;
        int j = height;
        if (i > j) {
            return;
        }
        int mode = nums[low];
        int temp;
        while (i != j) {
            while (mode >= nums[j] && i < j) {
                j--;
            }
            while (mode <= nums[i] && i < j) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        temp = nums[low];
        nums[low] = nums[i];
        nums[i] = temp;
        sort(nums, low, i - 1);
        sort(nums, i + 1, height);
    }

    /**
     * a>b 交换
     *
     * @param a
     * @param b
     */
    public void swap(int a, int b) {
        int temp;
        if (a > b) {
            temp = a;
            a = b;
            b = a;
        }
    }

    @Test
    public void main() {
        int s = 7;

        int[] nums = {2, 3, 1, 2, 4, 3};
        int res = minSubArrayLen1(s, nums);
        sort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        System.out.println(res);
    }
}
