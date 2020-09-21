package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 贝壳的笔试
 */
public class BK {

    @Test
    public void test() {
        int t = 1;
        String[] arr = new String[]{"J", "J", "S", "S",};

        String[][] m = new String[t][2];
        String[][] n = new String[t][2];
        int index = 0;
        for (int i = 0; i < 4 * t; i += 4) {
            m[index][0] = arr[i];
            m[index][1] = arr[i + 1];
            n[index][0] = arr[i + 2];
            n[index][1] = arr[i + 3];
            index++;
        }
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("JB", true);
        map.put("SJ", true);
        map.put("BS", true);
        String ans = getAns(m, n, t, map);
        System.out.println(ans);
    }

    public String getAns(String[][] m, String[][] n, int t, Map<String, Boolean> map) {
        int left = 0, right = 0;
        for (int i = 0; i < t; i++) {

            for (int j = 0; j < 2; j++) {

                String ll = m[i][0] + n[i][j];
                String rr = m[i][1] + n[i][j];
                if (map.getOrDefault(ll, false)) {
                    left++;
                }
                if (map.getOrDefault(rr, false)) {
                    right++;
                }

            }

        }
        if (left == right) {
            return "same";
        }
        return left > right ? "left" : "right";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int l = 0; l < t; l++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            for (int s = 1; s < m; s++) {
                Set<Integer> set = new HashSet<>();
                for (int g = 0; g < k; g++) {
                    set.add(in.nextInt());
                }
                map.put(s, set);
            }
            map.put(0, new HashSet<>());
        }
    }

    public static int dfs(int n, int m, int pre, Map<Integer, Set<Integer>> set) {
        if (n == 0) {
            return 1;
        }
        int sum = 0;
        for (int i = 1; i <= m; i++) {
            if (!set.get(pre).contains(i)) {
                sum += dfs(n - 1, m, i, set);
            }
        }
        return sum;
    }

    @Test
    public void test7() {
        System.out.println(getNum(5));
        long ans = (long) Math.pow(2, 4);
    }

    public long getNum(int n) {
        long res = 1;
        while (n > 0) {
            res = res * n;
            n--;
        }
        return res;
    }

    public void shuaQi() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        //可能得最大得数,第i块板子为第j种颜色的选择个数。。
        long[][] dp = new long[1005][15];
        //每个桩可选的油漆颜色
        int[][] p = new int[15][15];
        //使用c的人习惯1开始
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            for (int j = 1; j <= m; j++) {
                for (int l = 1; l <= k; l++) {
                    //用来标记下次不能使用的油漆的
                    p[j][l] = 1;
                }
            }
            //第一个桩可以有那么多选择。。。
            for (int j = 1; j <= m; j++) {
                dp[1][j] = 1;
            }
            int ans = 0;
            for (int j = 2; j <= n; j++) {
                for (int o = 1; o <= m; o++) {
                    for (int f = 1; f <= k; f++) {
                        if (p[o][k] == 1) {
                            continue;
                        }
                        //选择种类就像一个流往下走。。。
                        dp[j][k] = (dp[j - 1][o] + dp[j][f]) % 1000000007;
                    }
                }
            }
        }
    }
}
