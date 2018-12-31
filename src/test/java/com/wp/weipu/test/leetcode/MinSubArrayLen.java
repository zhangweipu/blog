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
        int min = n+1;
        for (int j = 0; j < n; j++) {
            int m = s;
            int index = 1;
            for (int i = j; i < n; i++) {
                if (nums[i] >= m) {
                    if (min > index) {
                        min = index;
                    }
                    break;
                } else {
                    m = m - nums[i];
                    index++;
                }
            }
        }
        return min>n?0:min;
    }

    /**
     * 使用一个循环实现
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n=nums.length;
        int head=0,prehead=0;
        int min=n+1;
        int m=s;
        int index=1;
        while (head<n){
            if(nums[head]>=m){
                if(min>index){
                    min=index;
                    System.out.println(index);
                }
                prehead++;
                head=prehead;
                m=s;
                index=1;
            }else {
                m=m-nums[head];
                head++;
                index++;
            }
        }
        return min>n?0:min;
    }

    /**
     * 从大到小排序先,不能用排序
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
        int s = 213;
        int[] nums = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        int res = minSubArrayLen2(s, nums);
        sort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(" " + nums[i] + " ");
        }
        System.out.println("\n");
        System.out.println(res);
    }
}
