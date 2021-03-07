package com.wp.weipu.test.review;

import java.util.PriorityQueue;

/**
 * 关于树的
 */
public class AboutTree {
    /**
     * 最后一块石头的重量
     * 使用优先队列
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int s : stones) {
            queue.offer(s);
        }
        while (queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();
            if (a != b) {
                queue.offer(a - b);
            }
        }
        if (queue.isEmpty()) {
            return 0;
        }
        //这里可能为空的
        return queue.poll();
    }

    /**
     * 最小跳跃次数
     *
     * @param jump
     * @return
     */
    public int minJump(int[] jump) {
        int len = jump.length;
        //dp数组 每一位代表该位到跳出的最少次数
        int[] dp = new int[len];
        //利用后面的dp来决定前面的
        for (int i = len - 1; i >= 0; i--) {
            //这一步我懂
            dp[i] = i + jump[i] >= len ? 1 : dp[i + jump[i]] + 1;
            //往前搜索，
            for (int j = i + 1; j < len && j < i + jump[i] && dp[j] > dp[i]; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[0];
    }
}
