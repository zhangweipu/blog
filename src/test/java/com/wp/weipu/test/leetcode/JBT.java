package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class JBT {

    @Test
    public void test() {
        String str = "a";
        String c1 = "A";
        String c2 = "b";
        str = str.toLowerCase();
        c1 = c1.toLowerCase();
        c2 = c2.toLowerCase();
        int c1min = str.indexOf(c1);
        int c1max = str.lastIndexOf(c1);
        int c2min = str.indexOf(c2);
        int c2max = str.lastIndexOf(c2);
        if (c2max == -1 || c1max == -1) {
            System.out.println(-1);
            return;
        }
        int ans = Math.max(c1max - c2min, c2max - c1min);
        System.out.println(ans);
    }

    @Test
    public void test2() {
//        int n = 6;
//        for (int i = 1; i < 2 * n; i += 2) {
//            System.out.println(i);
//        }
        int[] arr = new int[]{2, 5, 1, 3, 4, 7};
        shuffle(arr, 3);
    }

    public int[] shuffle(int[] nums, int n) {
        int[] nums1 = new int[2 * n];
        int l = 0, m = n;
        for (int i = 1; i < 2 * n; i += 2) {
            System.out.println(i);
//            nums1[i - 1] = nums[l];
//            nums1[i] = nums[m];
            l++;
            n++;
        }
        return nums1;
    }

    /**
     * 判断能不能进入所有的房间
     * 如果倒着来呢
     * 题意理解错了，他们能随便跳
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size() - 1;
        int i = n - 1;
        boolean flag = false;
        while (i >= 0) {
            List<Integer> nums = rooms.get(i);
            if (nums.contains(n)) {
                n = i;
                flag = true;
            } else {
                flag = false;
                i--;
            }
        }
        return flag;
    }

    /**
     * 可以来回跳的，回溯算法了
     * 深度遍历还不太会用
     *
     * @param rooms
     * @return
     */
    boolean[] visited;
    int num;

    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        visited = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    /**
     * 使用dfs
     */
    public void dfs(List<List<Integer>> rooms, int x) {
        visited[x] = true;
        num++;
        for (Integer i : rooms.get(x)) {
            if (!visited[i]) {
                dfs(rooms, i);
            }
        }
    }

    /**
     * X 的前后或者左右不能有x
     * 当一个位置的左面或者上面是X则跳过，这个点就是一个船
     *
     * @param board
     * @return
     */

    public int countBattleships(char[][] board) {
        int row = board.length, col = board[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                res++;
            }
        }
        return res;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        Stack<Integer> queue = new Stack<>();
        int n = graph.length;
        boolean[] visited = new boolean[n];
        dfs(graph, ans, queue, 0, n, visited);
        return ans;
    }

    public void dfs(int[][] graph, List<List<Integer>> list, Stack<Integer> queue, int x, int n, boolean[] visited) {
        if (x == n) {
            list.add(new ArrayList<>(queue));
            return;
        }
        for (int m : graph[x]) {
            if (!visited[m]) {
                visited[m] = true;
                queue.push(m);
                dfs(graph, list, queue, m, n, visited);
                queue.pop();
                visited[m] = false;
            }
        }
    }

    @Test
    public void test5() {

    }

    /**
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> arr = new LinkedList<>();
        addNum(ans, arr, 0, n, k);
        return ans;
    }

    public void addNum(List<List<Integer>> ans, LinkedList<Integer> arr, int i, int n, int k) {
        if (arr.size() == k) {
            ans.add(new LinkedList<>(arr));
        }
        for (int j = i; j <= n; j++) {
            arr.add(j);
            addNum(ans, arr, j + 1, n, k);
            arr.removeLast();
        }
    }

    /**
     * 动态规划可不可以用
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] nums = new int[num];
        for (int i = 1; i <= num; i++) {
            int n = 1, m = i;
            while (m > 0) {
                if ((m & 1) == 1) {
                    n++;
                }
                m >>= 1;
            }
            nums[i] = n;
        }
        return nums;
    }
    public int[] countBits2(int num) {
        int[] res = new int[num+1];
        res[0] = 0;

        for(int i = 1; i <= num; i++)
        {
            res[i] = res[i >> 1] + (i&1);
        }
        return res;
    }
    @Test
    public void test8() {
        int m = 3;
        m >>= 1;
        System.out.println(m);
    }
}
