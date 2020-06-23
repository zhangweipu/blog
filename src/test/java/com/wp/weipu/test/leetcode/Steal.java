package com.wp.weipu.test.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 打家劫舍相关
 * 滚动数组，每时每刻只存储两间房子的最高金额
 */
public class Steal {
    /**
     * 每一家都有偷和不偷的两个状态。偷和不偷可以隔一家或者两家
     * 所以需要保存前两次的状态
     *
     * @param arr
     */
    public int rob(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        //不必要的一个判断
//        if (arr.length == 2) {
//            return Math.max(arr[0], arr[1]);
//        }
        int[] s = new int[arr.length];
        // 保存前两个的状态
        s[0] = arr[0];
        // 可能最大是第一个，也可能是第二个。
        s[1] = Math.max(s[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            //这一步用了推导公式
            s[i] = Math.max(s[i - 2] + arr[i], s[i - 1]);
        }
        return s[arr.length - 1];
    }

    /**
     * 使用滚动数组，代替数组
     *
     * @param arr
     * @return
     */
    public int rob1(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int first = arr[0];
        int second = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            int tmp = second;
            second = Math.max(first + arr[i], second);
            first = tmp;
        }
        return second;
    }

    /**
     * 通过层次遍历,这个复杂度会比较高，
     * 计算爷和孙的节点的和，与父节点进行比较
     *  递归，这里具有重复问题。优化，做记忆化。。。。
     *  使用哈希表来作为二叉树的缓存。。保存已经访问过的节点和值
     *  空间换时间
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }
        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }
        return Math.max(money, (rob(root.left) + rob(root.right)));
    }

}
