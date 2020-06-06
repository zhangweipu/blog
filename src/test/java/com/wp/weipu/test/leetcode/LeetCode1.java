package com.wp.weipu.test.leetcode;

import org.junit.Test;

public class LeetCode1 {

    public int[] spiralOrder(int[][] matrix) {
        //不通过for执行
        int row = matrix.length;
        int col = matrix[1].length;
        int num = row * col;
        int[] res = new int[num];
        int index = 0;
        int left = 0, right = col - 1, top = 0, bottom = row - 1;
        while (left <= right && top <= bottom) {
            //从左到右
            for (int i = left; i <= right; i++) {
                res[index] = matrix[top][i];
                index++;
            }
            // 从top的下面开始
            top++;
            // 从上到下
            for (int i = top; i <= bottom; i++) {
                res[index] = matrix[i][right];
                index++;
            }
            right--;
            //这一句是防止重复
            if (index == num) {
                break;
            }
            //从右到左
            for (int i = right; i >= left; i--) {
                res[index] = matrix[bottom][i];
                index++;
            }
            bottom--;
            //从下到上
            for (int i = bottom; i >= top; i--) {
                res[index] = matrix[i][left];
                index++;
            }
            left++;
        }
        return res;
    }

    @Test
    public void test() {
        int[][] arr = {{1, 2, 3, 3}, {4, 7, 5, 6}, {7, 8, 6, 9}};
        int[] res = spiralOrder(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
