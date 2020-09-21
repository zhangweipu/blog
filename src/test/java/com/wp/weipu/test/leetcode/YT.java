package com.wp.weipu.test.leetcode;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class YT {

    public static int minDistinct(int n, int[][] nums) {
        double min = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 1; i < n; i++) {
            double ss = getDistinct(nums[i - 1], nums[i]);
            if (ss > min) {
                min = ss;
                index = i;
            }
        }
        return index;
    }

    public static double getDistinct(int[] a1, int[] a2) {
        int t1 = a1[0];
        int t2 = a2[0];
        double x = Math.pow((a1[1] - a2[1]), 2);
        double y = Math.pow((a1[2] - a2[2]), 2);
        double z = Math.pow((a1[3] - a2[3]), 2);
        double d = Math.sqrt(x + y + z) / (t2 - t1);
        System.out.println(d);
        return d;
    }

    public static void main(String[] args) {
//        int n = 3;
//        int[][] nums = new int[][]{
//                {2, -9, 2, 3},
//                {4, 9, 9, 8},
//                {9, 2, 3, 4}};
//        System.out.println(minDistinct(n, nums));
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int k = 0; k < t; k++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int[] cost = new int[4];
            for (int i = 0; i < 4; i++) {
                cost[i] = in.nextInt();
            }
            int[][] direction = new int[][]{{-1, 0, cost[0]}, {1, 0, cost[1]}, {0, -1, cost[2]}, {0, 1, cost[3]}};
            String[][] grid = new String[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = in.next();
                }
            }
            grid[0][0] = "x";
            int ans = getNum(grid, x, y, 0, 0, 0, direction);
            System.out.println(ans);
        }
    }

    public static int getNum(String[][] grid, int x, int y, int i, int j, int nums, int[][] direction) {
        int n = grid.length;
        int m = grid[0].length;

        if (x == i && y == j) {
            System.out.println("--------");
            return nums;
        }
        int max = Integer.MAX_VALUE;
        for (int[] dir : direction) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi < 0 || newi >= n || newj < 0 || newj >= m || "x".equals(grid[newi][newj])) {
                continue;
            }
            //走过的路要标记一下啊
            grid[newi][newj] = "x";
            System.out.println(dir[2]);
            max = Math.min(getNum(grid, x, y, newi, newj, nums + dir[2], direction), max);
            grid[newi][newj] = "o";
        }
        return max;
    }

}
