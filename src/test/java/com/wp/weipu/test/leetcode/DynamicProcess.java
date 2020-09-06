package com.wp.weipu.test.leetcode;

/**
 * 动态规划问题集
 */
public class DynamicProcess {
    /**
     * 合并金币问题
     * 1 2 3 得 9
     * 每合并两个，则两个的和为得分。
     *
     * @param money
     */
    public int mergeMoney(int[] money) {
        int n = money.length;
        //这个不用写出来
        if (n == 2) {
            return money[0] + money[1];
        }
        int[] sum = new int[n];
        sum[0] = money[0];
        for (int i = 1; i < n; i++) {
            //保存
            sum[i] = sum[i - 1] + money[i];
        }
        int[][] dp = new int[n + 1][n + 1];
        //区间长度
        for (int len = 2; len <= n; len++) {
            //区间起始位置
            for (int i = 0; i <= n - len + 1; i++) {
                //区间结束位置
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                int sum1 = sum[j] - sum[i - 1];
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j] + sum1);
                }
            }
        }
        return dp[0][n];
    }
}
