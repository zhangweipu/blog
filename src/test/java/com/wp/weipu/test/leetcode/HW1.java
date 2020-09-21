package com.wp.weipu.test.leetcode;


import org.junit.Test;

import java.util.*;

public class HW1 {
    /**
     * 四个方向一般是找最小的那个吧
     *
     * @param grid
     * @param i
     * @param j
     */
    static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 要不要设置标志
     *
     * @param grid
     * @param i
     * @param j
     * @param len
     * @return
     */
    public static int dfs(int[][] grid, int i, int j, int last, int len) {
        int n = grid.length;
        int m = grid[0].length;
        int max = 1;

        for (int[] dir : direction) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI < 0 || newI >= n || newJ < 0 || newJ >= m || last <= grid[newI][newJ]) {
                continue;
            }
            int leng = dfs(grid, i + dir[0], j + dir[1], grid[newI][newJ], len + 1) + 1;
            System.out.println(leng);
            max = Math.max(max, leng);
        }
        return max;
    }

    /**
     * note:错的
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public static boolean hasWay(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        int l = 4;
        //这块基本上没用了
        for (int[] dir : direction) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            //这个表示没有路
            if (newI < 0 || newI >= n || newJ < 0 || newJ >= m || grid[i][j] <= grid[newI][newJ]) {
                l--;
            }
        }
        //不等于0说明有路径
        return l != 0;
    }

//    public static void main(String[] args) {
//        int[][] grid = new int[][]{{8, 4, 1}, {6, 5, 2}};
//        int n = 2, m = 3;
//        int max = 0;
//        int[][] hasWay = new int[n][m];
//        int maxI = 0, maxJ = 0, maxValue = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                max = Math.max(max, dfs(grid, i, j, grid[i][j], 0));
//            }
//        }
//
//
//        System.out.println(max);
//    }

    /**
     * dp可以但是要n*m次
     *
     * @param grid
     * @return
     */
    public int getLen(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        //初始的高度
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean both = true;
                //不可以啊
                int a = 0, b = 0, c = 0, d = 0;
                if (i - 1 >= 0 && grid[i][j] < grid[i - 1][j]) {
                    a = dp[i - 1][j] + 1;
                }
                if (j - 1 >= 0 && grid[i][j] < grid[i - 1][j]) {
                    b = dp[i][j - 1] + 1;
                }
                if (i + 1 < n && grid[i][j] < grid[i - 1][j]) {
                    c = dp[i + 1][j] + 1;
                }
                if (j + 1 < m && grid[i][j] < grid[i - 1][j]) {
                    d = dp[i][j + 1] + 1;
                }
                dp[i][j] = Math.max(Math.max(a, b), Math.max(c, d));
            }
        }
        //动态规划不合适吧，没有规律。。。。没办法按顺序
        return 0;
    }

    public void getLoad(int[][] grid, int i, int j, int[][] dp) {
        //表示还没访问过
        if (dp[i][j] == 0) {
            return;
        }
        int n = grid.length, m = grid[0].length;
        dp[i][j] = 1;
        for (int[] dir : direction) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI >= 0 && newI < n && newJ >= 0 && newJ < m && grid[i][j] > grid[newI][newJ]) {
                getLoad(grid, newI, newJ, dp);
                dp[newI][newJ] = Math.max(dp[newI][newJ], dp[i][j] + 1);
            }
        }
    }


    /**
     * 构建树
     *
     * @param arr
     * @return
     */
    public int getAndOr(int[][] arr) {
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int[] a : arr) {
            TreeNode root = new TreeNode(a[0], a[1], a[2], a[3]);
            map.put(a[0], root);
        }
        //建树
        //怎么找到根的
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            TreeNode root = entry.getValue();
            if (root.leftId != -1) {
                root.left = map.get(root.leftId);
            }
            if (root.rightId != -1) {
                root.right = map.get(root.rightId);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            TreeNode root = entry.getValue();
            max = Math.max(max, dfs(root, 0));
        }
        return max;
    }

    int dfs(TreeNode root, int value) {
        if (root == null) {
            return value;
        }
        value = value ^ root.weight;
        int v1 = dfs(root.left, value);
        int v2 = dfs(root.right, value);
        return Math.max(v1, v2);
    }

    class TreeNode {
        int id;
        int weight;
        int leftId;
        int rightId;
        TreeNode left;
        TreeNode right;

        TreeNode(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        TreeNode(int id, int weight, int leftId, int rightId) {
            this.id = id;
            this.weight = weight;
            this.leftId = leftId;
            this.rightId = rightId;
        }
    }

    @Test
    public void test() {
        List<List<Integer>> res = combinationSum3(3, 7);
        System.out.println(res.toString());
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        //不满足的情况
        if (k > n || n > 45) {
            return null;
        }
        int[] color = new int[10];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            color[i] = 1;
            LinkedList<Integer> nums = new LinkedList<>();
            nums.add(i);
            track(res, nums, i, color, k, n);
            nums.removeLast();
            color[i] = 0;
        }
        return res;
    }

    public void track(List<List<Integer>> res, LinkedList<Integer> nums, int sum, int[] color, int k, int n) {
        if (nums.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(nums));
            }
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (nums.peekLast() > i) {
                continue;
            }
            if (color[i] != 1 && sum < n) {
                color[i] = 1;
                nums.add(i);
                track(res, nums, sum + i, color, k, n);
                nums.removeLast();
                color[i] = 0;
            }
        }
    }

    public static List<Integer> getIds(int n, int m, int[][] nums) {
        List<Integer> ids = new ArrayList<>();
        int[][] first = new int[1][2];
        first[0][0] = nums[0][0];
        first[0][1] = nums[0][1];
        Set<Integer> leave = new HashSet<>();
        for (int i = 1; i < m - 1; i++) {
            //这都是在最后打卡的
            leave.add(nums[i][0]);
        }
        if (first[0][1] == 0) {
            leave.add(first[0][0]);
        }
        //不知道多余不
        if (nums[m - 1][1] == first[0][0]) {
            leave.remove(nums[0][0]);
        }

        for (int i = 1; i <= n; i++) {
            if (!leave.contains(i)) {
                ids.add(i);
            }
        }
        return ids;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }
        List<Integer> res = getIds(n, m, arr);
        for (int l : res) {
            System.out.print(l + " ");
        }
    }

    @Test
    public void test3() {
        int[][] arr = new int[][]{{5, 1}, {1, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 0}, {4, 0}, {5, 0}};
        List<Integer> id = getIds(5, 8, arr);
        System.out.println(id.toString());
    }
}
