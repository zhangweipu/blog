package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class TH {
    public long sum(int num, int itemNum) {
        // write code here
        int ans = 0;
        int[] arr = new int[itemNum];
        arr[0] = 3;
        for (int i = 1; i < itemNum; i++) {
            arr[i] = arr[i - 1] * 10 + num;
            System.out.println(arr[i]);
        }
        return Arrays.stream(arr).sum();
    }

    @Test
    public void test() {
        System.out.println(sum(3, 5));
        System.out.println(3 + 33 + 333 + 3333 + 33333);
    }

    /**
     * 这里面没有坑，是我想多了。。。。【
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        // write code here
        int i = 0, j = nums.length - 1;
        int sum = 0;
        int mid = nums[(i + j) / 2];
        while (i < j) {
            sum += (nums[++i] - mid);
        }
        return sum;
    }

    class CQueue {
        Stack<Integer> input;
        Stack<Integer> output;

        public CQueue() {
            this.input = new Stack<>();
            this.output = new Stack<>();
        }

        public void appendTail(int value) {
            if (output.isEmpty()) {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
            input.push(value);
        }

        public int deleteHead() {
            //这种情况可以没有else
            if (!output.isEmpty()) {
                return output.pop();
            }
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            if (output.isEmpty()) {
                return -1;
            }
            return output.pop();
        }
    }

}
