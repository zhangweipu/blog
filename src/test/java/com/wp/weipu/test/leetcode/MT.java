package com.wp.weipu.test.leetcode;

import java.util.*;

/**
 * 美团相关
 */
public class MT {
    /**
     * 回文的最大个数
     *
     * @param word
     * @return
     */
    public int getMaxHuiWen(String word) {
        int n = word.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                StringBuilder sb = new StringBuilder(word.substring(i, j));
                if (isHuiWen(sb)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isHuiWen(StringBuilder word) {
        if (word.length() == 1) {
            return true;
        }
        Map<String, Integer> map = new HashMap<>();
        map.merge("dd", 1, Integer::sum);
        return word.toString().equals(word.reverse().toString());
    }

    /**
     * 首个父节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode parent = null;
        backtrack(parent, root, p, q);
        return parent;
    }

    public boolean backtrack(TreeNode parent, TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return true;
        }
        boolean left = backtrack(parent, root.left, p, q);
        boolean right = backtrack(parent, root.right, p, q);
        if (left && right && parent != null) {
            parent = root;
        }
        return left || right;
    }

    /**
     * 难点在于怎么判断可达
     * 通过map实现深度遍历，
     * 构建邻接矩阵
     *
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        int[][] mat = new int[n][n];
        for (int[] arr : connections) {
            //怎么判断方向啊
            mat[arr[0]][arr[1]] = 1;
            mat[arr[1]][arr[0]] = -1;
        }
        Set<Integer> can = new HashSet<>();
        //可以直接到达0的
        for (int i = 1; i < n; i++) {
            if (mat[i][0] == -1) {
                can.add(i);
            }
        }
        //间接到达0的路径最长为n-1
        while (n - 1 > 0) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {

                }
            }
            n--;
        }

        return 0;
    }

}
