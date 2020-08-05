package com.wp.weipu.test.questionOnInterview;

import org.junit.Test;

import java.util.Arrays;

public class AliQuestions {

    /**
     * 给定一个长度为m的数组a，给定一个数n;
     * 求解区间中出现的数不小于n的区间的个数
     * 例如：[1,2,1,2,3,4,5] m=6,n=2
     * 正确的 区间 有[1,3][1,4][1,5][1,6][2,4][2,5][2,6]
     * 不正确的区间有[3,4][4,5]等
     */

    public void countNums() {

    }

    /**
     * 给定n
     * 例如 n=4
     * 求一组小于n的数，使两两相邻的数的绝对值大于1；
     * 例如：[1,3,0,2]
     */

    public void getNums(int n) {

        int[] color = new int[n];
        //表示没有使用
        Arrays.fill(color, -1);
        int num = 0;
        for (int i = 0; i < n; i++) {
            color[i] = 1;
            num += getNum(color, i, n, n - 1);
            color[i] = -1;
        }
        System.out.println(num);
    }

    public int getNum(int[] nums, int pre, int n, int sub) {
        if (sub == 0) {
            //想返回 在这个地方还要完成复制
            return 1;
        }
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == -1 && Math.abs(i - pre) > 1) {
                nums[i] = 1;
                num += getNum(nums, i, n, sub - 1);
                nums[i] = -1;
            }
        }
        return num;
    }

    @Test
    public void test1() {
        getNums(5);
    }
}
