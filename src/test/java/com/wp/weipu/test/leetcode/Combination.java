package com.wp.weipu.test.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 排列组合和概率
 */
public class Combination {

    public int[] number = {1, 2, 2, 3, 5, 6};
    public int n = 6;
    //标记使用过的数
    public boolean[] visited = new boolean[n];
    //使用二维数组，表示图
    public int[][] graph = new int[n][n];
    //数字组合
    public StringBuffer sb = new StringBuffer("");
    //去重的
    Set<String> s = new HashSet<>();

    /**
     * @param start
     */
    public void depthSearch(int start) {
        visited[start] = true;
        sb.append(number[start]);
        if (sb.length() == n) {
            if (sb.indexOf("4") != 2) {
                s.add(sb.toString());
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[start][i] == 1) {
                depthSearch(i);
            }
        }
        //还原字符串到上一级
        sb.delete(sb.length() - 1, sb.length());
        visited[start] = false;
    }

    public void getAllCombination() {
        //构造图
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 1;
                }
            }
        }
        graph[3][5] = 0;
        graph[5][3] = 0;
        for (int i = 0; i < n; i++) {
            depthSearch(i);
        }
    }

}
