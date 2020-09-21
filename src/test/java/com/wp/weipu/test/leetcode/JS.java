package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class JS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num = 2 * (n + 1) - 1;
        int[][] mat = new int[n + 1][num + 1];
        for (int j = 1; j < n + 1; j++) {
            mat[j][1] = 1;
            mat[j][2 * j - 1] = 1;
        }
        System.out.println(1);
        for (int i = 2; i < n + 1; i++) {
            System.out.print(1 + " ");
            for (int j = 2; j < 2 * i - 1; j++) {
                if (j <= i) {
                    mat[i][j] = mat[i - 1][j - 1] + mat[i - 1][j - 2];
                }
                if (j > i) {
                    mat[i][j] = mat[i - 1][j] + mat[i - 1][j - 1];
                }
                System.out.print(mat[i][j] + " ");
            }
            System.out.println(mat[i][2 * i - 1]);
        }
    }

    public JSTree buildTree(int[] arr) {
        int n = arr.length;
        JSTree root = new JSTree(arr[0]);
        Stack<JSTree> stack = new Stack<>();
        stack.push(root);
        for (int i = 1; i < n - 2; i++) {
            //直接跳过去
            if (arr[i] == -1 && arr[i + 1] == -1) {
                i++;
                stack.pop();
                continue;
            }
            JSTree tmp = stack.peek();
            if (tmp.left == null && arr[i] != -1) {
                tmp.left = new JSTree(arr[i]);
                stack.push(tmp.left);
                continue;
            }
            if (tmp.right == null && arr[i + 1] != -1) {
                tmp.right = new JSTree(arr[i + 1]);
                stack.push(tmp.right);
            }
        }
        return root;
    }

    @Test
    public void test() {
        int[] arr = {3, 5, 6, -1, -1, 2, 7, -1, -1, 4, -1, -1, 1, 9, -1, -1, 8, -1, -1};
        List<Integer> lis= new LinkedList<>();
        


    }

    class JSTree {
        int val;
        JSTree left;
        JSTree right;
        public JSTree(int val) {
            this.val = val;
        }
       public JSTree() {

       }
    }

}
