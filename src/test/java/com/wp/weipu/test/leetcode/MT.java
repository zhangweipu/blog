package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

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

    /**
     * 填字游戏
     *
     * @return
     */
    public int getNums(int n, int k, int d) {
        //判断最大值范围
        int sum = 0;
        //保证最大的值大于等于d,还要去重啊
        for (int i = d; i <= k; i++) {
            sum += backtrace(n - i, i, k, 1);
        }
        return sum;
    }

    public int backtrace(int target, int g, int k, int loc) {
        if (target == 0) {
            return loc;
        }
        int sum = 0;
        for (int i = 1; i <= k; i++) {
            if (target >= i) {
                sum += backtrace(target - i, i, k, loc + 1);
            }
        }
        return sum;
    }


    @Test
    public void test6() {
        System.out.println(getNums(5, 3, 2));
    }

    public static int method(int[] a, int m, int k) {
        if (a.length == 0 || m == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i <= a.length - m; ++i) {
            if (a[i] < k) {
                continue;
            }
            int j = 1;
            for (; j < m; ++j) {
                if (a[i + j] < k) {
                    i = i + j;
                    break;
                }
            }
            if (j == m) {
                result += 1;
            }
        }
        return result;
    }

    @Test
    public void test9() {
//        int[] arr = new int[5];
//        System.out.println(method(arr, 3, 2));
        int[][] arr = new int[][]{{1, 0, 1}, {0, 1, 0}, {0, 1, 0}, {1, 0, 1}, {1, 0, 1}, {0, 1, 0}};
        System.out.println(JSON.toJSONString(searchMat(6, 3, arr)));
    }

    public int[][] searchMat(int n, int m, int[][] mat) {
        StringBuilder[] ss = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(mat[i][j]);
            }
            ss[i] = sb;
        }
        int line = 0;
        //转化成查找回文数组
        for (int i = 1; i < ss.length; i += 2) {
            int j = 0, k = i;
            boolean flag = false;
            while (j < k) {
                //查找最早的回文串
                if (ss[j].reverse().toString().equals(ss[k].toString())) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
                j++;
                k--;
            }
            if (flag) {
                line = i - 1;
                break;
            } else {
                line = i;
            }
        }
        int[][] newMat = new int[line][m];
        for (int i = 0; i < line; i++) {
            if (m >= 0) System.arraycopy(mat[i], 0, newMat[i], 0, m);
        }
        return newMat;
    }


}
