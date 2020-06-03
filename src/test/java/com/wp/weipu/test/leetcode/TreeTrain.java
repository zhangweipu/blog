package com.wp.weipu.test.leetcode;

import org.junit.Test;

public class TreeTrain {
    // 用了全局变量
    int maxNum = Integer.MIN_VALUE;
    TreeNode maxSubTree = null;

    public int findMaxSubTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int sum = root.val;
        if (root.left != null) {
            sum += findMaxSubTree(root.left);
        }
        if (root.right != null) {
            sum += findMaxSubTree(root.right);
        }
        if (maxNum < sum) {
            maxNum = sum;
            maxSubTree = root;
        }
        return sum;
    }

    TreeNode pHead = null, pEnd = null;

    public void changeTreeToList(TreeNode root) {
        if (root == null) {
            return;
        }
        // 转换左子树
        if (root.left != null) {
            changeTreeToList(root.left);
        }
        root.left = pEnd;
        if (null == pEnd) {
            //当pEnd为空时，说明是在最左的叶子
            pHead = root;
        } else {
            pEnd.right = root;
        }
        pEnd = root;
        //转化右子树
        if (root.right != null) {
            changeTreeToList(root.right);
        }
    }

    /**
     * 判断数组是否是二元查找树的后序遍历的序列，
     * 二元查找树，左子树小于右子树
     *
     * @param arr
     * @param start
     * @param end
     */
    public boolean isPostOrder(int[] arr, int start, int end) {
        if (arr == null) {
            return false;
        }
        int root = arr[end];
        int i, j;
        for (i = 0; i < end; i++) {
            if (arr[i] > root) {
                break;
            }
        }
        for (j = i; j < end; j++) {
            if (arr[j] < root) {
                return false;
            }
        }
        boolean leftIs = true;
        boolean rightIs = true;
        if (i > start) {
            leftIs = isPostOrder(arr, start, i - 1);
        }
        if (j < end) {
            rightIs = isPostOrder(arr, i, end - 1);
        }
        return leftIs && rightIs;
    }

    class MaxInfo {
        // 定义这个只为了保存变量
        int val;
    }

    public int maxPathSum(TreeNode root, MaxInfo info) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxPathSum(root.left, info);
        int rightMax = maxPathSum(root.right, info);
        int allMax = root.val + leftMax + rightMax;
        int leftSum = leftMax + root.val;
        int rightSum = rightMax + root.val;
        int tmpMax = maxThree(allMax, leftSum, rightSum);
        // 确保路径最大
        info.val = Math.max(tmpMax, info.val);
        int sumMax = Math.max(leftMax, rightMax);
        return root.val + sumMax;
    }

    public int maxThree(int a, int b, int c) {
        int max;
        max = Math.max(a, b);
        max = Math.max(c, max);
        return max;
    }

    @Test
    public void test1() {
        int[] arr = {1, 3, 2, 5, 7, 6, 3};
        if (isPostOrder(arr, 0, arr.length - 1)) {
            System.out.println("是");
        } else {
            System.out.println("不是");
        }
    }
}
