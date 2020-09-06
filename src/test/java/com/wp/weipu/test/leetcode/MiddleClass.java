package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//中等难度级别
public class MiddleClass {
    /**
     * 装最多水的容器，上一次采用的是暴力算法，不好。。
     * 还真是双指针
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int len = height.length;
        if (len == 2) {
            return Math.min(height[0], height[1]);
        }
        int pre = 0, tail = len - 1;
        int maxPool = 0;
        while (pre < tail) {
            int area = 0;
            if (height[pre] < height[tail]) {
                area = (tail - pre) * height[pre];

                pre++;
            } else if (height[pre] > height[tail]) {
                area = (tail - pre) * height[tail];
                tail--;
            } else {
                pre++;
            }
            maxPool = Math.max(maxPool, area);
        }
        return maxPool;
    }

    /**
     * 树转化称链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        TreeNode r = recuTree(root);
    }

    public TreeNode recuTree(TreeNode treeNode) {
        if (treeNode == null) {
            return treeNode;
        }
        if (treeNode.left == null && treeNode.right == null) {
            return treeNode;
        }
        TreeNode left = treeNode.left, right = treeNode.right;
        treeNode.left = null;
        treeNode.right = left;
        if (left != null) {
            recuTree(left);
        }
        while (left != null) {
            left = left.right;
        }
        left.right = right;
        if (right != null) {
            recuTree(right);
        }
        return treeNode;
    }

    public int getKProduce(int n, int k) {
        if (n < k) {
            return 0;
        }
        int[] produce = new int[n];
        for (int i = 0; i < n; i++) {
            produce[i] = i + 1;
        }
        boolean flag = true;
        int tmpK = k;
        int num = 0;
        int nump = n;
        //倒叙会更好吧
        while (flag) {
            for (int i = 0; i < n; i++) {
                //集齐一个福袋
                if (tmpK == 0) {
                    num++;
                    tmpK = k;
                }
                if (produce[i] > 0) {
                    tmpK--;
                    produce[i]--;
                } else {
                    //剩余的种类
                    nump--;
                }
            }
            //剩余的种类不够k种时退出
            if (nump < k) {
                flag = false;
            }
        }
        return num;
    }

    @Test
    public void test() {
        System.out.println(getKProduce(4, 3));
    }

    /**
     * 扫雷
     * 待做
     *
     * @param board
     * @param click 只有两个值
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        }
        return null;
    }

    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1},
            {1, 1}, {-1, 1}, {1, -1}};

    /**
     * 旁边有几个地雷
     *
     * @param x
     * @param y
     * @param board
     */
    public void check(int x, int y, char[][] board) {
        int count = 0;
        int i = board.length - 1;
        int j = board[0].length - 1;
        //检查四周有没有地雷
        for (int[] dire : directions) {
            int newX = x + dire[0];
            int newY = y + dire[1];
            //检查范围
            if (newX < 0 || newX > i || newY < 0 || newY > j || board[newX][newY] == 'B') {
                continue;
            }
            if (board[newX][newY] == 'M') {
                count++;
            }
        }
        //没有就是b
        if (count == 0) {
            board[x][y] = 'B';
        } else {
            board[x][y] = (char) count;
        }
    }

    /**
     * 广度遍历
     *
     * @param x
     * @param y
     * @param board
     */
    public void search(int x, int y, char[][] board) {
        //先检查是不是和地雷挨着的
        check(x, y, board);
        int i = board.length;
        int j = board[0].length;

        if (board[x][y] == 'B') {
            for (int[] dire : directions) {
                int newX = x + dire[0];
                int newY = y + dire[1];
                if (newX < 0 || newX >= i || newY < 0 || newY >= j || board[newX][newY] != 'E') {
                    continue;
                }
                search(newX, newY, board);
            }
        }
    }

    @Test
    public void test4() {
        int[] arr = new int[]{1, 9, 1, 2};
        if (judgePoint24(arr)) {
            System.out.println("是");
        } else {
            System.out.println("否");
        }
    }

    /**
     * 只能对百分之九十吧
     * 精度的问题，或者有别的问题
     *
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        int len = nums.length;
        double[] arr = new double[len];
        for (int i = 0; i < len; i++) {
            arr[i] = nums[i];
        }
        int[] colored = new int[len];
        return getNum(arr, colored, 24);
    }

    public boolean getNum(double[] nums, int[] colored, double target) {
        System.out.println(target);
        if (Math.abs(target) < 0.00001) {
            return true;
        }
        boolean res = false;
        for (int i = 0; i < nums.length; i++) {
            if (colored[i] == 0) {
                colored[i] = 1;
                res = res | getNum(nums, colored, target - nums[i]) |
                        //除法这里不好处理
                        getNum(nums, colored, target / nums[i]) |
                        getNum(nums, colored, target * nums[i]) |
                        getNum(nums, colored, target + nums[i]);
                colored[i] = 0;
                if (res) {
                    return res;
                }
            }
        }
        return false;
    }

    /**
     * 螺旋矩阵
     * 还有数量的限制
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int c = Math.min(m, n) / 2;
        int c2 = 0;
        List<Integer> list = new ArrayList<>();
        int count = m * n;
        while (c2 <= c && count > 0) {
            for (int i = c2; count > 0 && i < n - c2; i++) {
                list.add(matrix[c2][i]);
                count--;
            }
            for (int i = c2 + 1; count > 0 && i < m - c2; i++) {
                list.add(matrix[i][n - c2 - 1]);
                count--;
            }
            for (int i = n - c2 - 2; count > 0 && i >= c2; i--) {
                //注意数组
                list.add(matrix[m - c2 - 1][i]);
                count--;
            }
            for (int i = m - c2 - 2; count > 0 && i > c2; i--) {
                list.add(matrix[i][c2]);
                count--;
            }
            c2++;
        }
        return list;
    }

    @Test
    public void test7() {
        int[][] mat = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> res = spiralOrder(mat);
        res.forEach(System.out::println);

    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double tmp = myPow(x, Math.abs(n / 2));
        if (n / 2 % 2 == 1) {
            tmp = tmp * tmp * x;
        } else {
            tmp = tmp * tmp;
        }
        if (n > 0) {
            return tmp;
        } else {
            return 1 / tmp;
        }
    }

    @Test
    public void test39(){
        System.out.println(Integer.MIN_VALUE);
    }
}
