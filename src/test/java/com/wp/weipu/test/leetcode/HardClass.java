package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 难度级别为难的
 */
public class HardClass {
    /**
     * 使用动态规划做,求什么最值的吧，我忘了
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int len = nums.length;
        //需要加-1 和 len 两个数
        int[][] dp = new int[len + 2][len + 2];
        int[] val = new int[len + 2];
        val[0] = val[len - 1] = 1;
        for (int i = 1; i < val.length - 2; i++) {
            val[i] = nums[i - 1];
        }
        //从后往前计算
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 2; j <= len + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        //不是很明白
        return dp[0][len - 1];
    }

    /**
     * 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        if (n < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int sum = 0;
        //最后四个的和小于target 则不存在
        for (int i = n - 1; i >= n - 4; i--) {
            sum += nums[i];
        }
        if (sum < target) {
            return new ArrayList<>();
        }
        sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += nums[i];
        }
        if (sum > target) {
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (i < j && nums[j] == nums[j - 1]) {
                    continue;
                }
                sum = nums[i] + nums[j];
                //开始使用双指针的形式
                int l = j + 1, m = n - 1;
                while (l < m) {
                    int tar = sum + nums[l] + nums[m];
                    if (tar == target) {
                        //这个地方有点多余
                        lists.add(addNum(nums[i], nums[j], nums[l], nums[m]));
                        while (l < m && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < m && nums[m] == nums[m - 1]) {
                            m--;
                        }
                        l++;
                        m--;
                    }
                    if (tar < target) {
                        l++;
                    }
                    if (tar > target) {
                        m--;
                    }
                }

            }
        }
        return lists;
    }

    /**
     * 加法运算
     *
     * @param one
     * @param two
     * @param three
     * @param four
     * @return
     */
    public List<Integer> addNum(int one, int two, int three, int four) {
        List<Integer> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        return list;
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> res = fourSum(nums, 0);
        System.out.println(JSON.toJSONString(res));
    }
}
