package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class MinStack {

    LinkedList<Integer> sorts = null;
    PriorityQueue<Integer> queue = null;

    public MinStack() {
        sorts = new LinkedList<>();
        queue = new PriorityQueue<>();
    }


    public void push(int x) {
        sorts.addFirst(x);
        queue.offer(x);
    }

    public void pop() {
        int n = sorts.removeFirst();
        queue.remove(Integer.valueOf(n));
    }

    public int top() {
        return sorts.get(0);
    }

    public int min() {
        int min = queue.peek();
//        sorts.remove(Integer.valueOf(min));
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();
        minStack.pop();
        minStack.top();
        minStack.min();

    }

    public int strToInt(String str) {
        List<Character> ls = new ArrayList<>();
        int n = str.length();
        boolean l = false;
        //
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(str.charAt(i))) {
                ls.add(str.charAt(i));
                if (i - 1 >= 0 && str.charAt(i) == '-') {
                    l = true;
                }
            }
        }
        //
        return 0;
    }

    public boolean verifyPostorder(int[] postorder) {
        //反转数组再判断会简单一些
        int n = postorder.length;
        if (n == 0) {
            return true;
        }

        return true;
    }

    @Test
    public void test8() {
        int[] arr = new int[]{1, 3, 2, 6, 5};
        System.out.println(verifyPostorder(arr));
    }

    //暴力啊
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        int[][] visited = new int[m][n];
        int ans = 1;
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0], y = tmp[1];
            for (int i = 0; i < 2; i++) {
                int nex = dx[i] + x;
                int ney = dy[i] + y;
                if (nex >= m || nex < 0 || ney >= n || ney < 0 || getSum(nex) + getSum(ney) > k || visited[nex][ney] == 1) {
                    continue;
                }
                visited[nex][ney] = 1;
                queue.add(new int[]{nex, ney});
                ans++;
            }
        }
        return ans;
    }

    public int getSum(int num) {
        // if(num<10){
        //     return num;
        // }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queue.peek();
        Set<Integer> set = new HashSet<>();
        if (num == 100) {
            return 1;
        }
        int a = num / 10;
        int b = num % 10;
        return a + b;
    }

    /**
     * 双指针加map的形式
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int n = s.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)));
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j);
        }
        return max;
    }

    int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    board[i][j]='0';
                    if (dfs(board, i, j, word, 1)) {
                        return true;
                    }
                    board[i][j]=word.charAt(0);
                }
            }
        }
        return false;
    }

    /**
     * 路径里带不带环
     *
     * @param board
     * @param word
     * @param index
     * @return
     */
    public boolean dfs(char[][] board, int x, int y, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        int n = board.length, m = board[0].length;
        boolean flag = false;
        for (int[] dire : direction) {
            int newx = x + dire[0];
            int newy = y + dire[1];
            if (newx < 0 || newx >= n || newy < 0 || newy >= m || board[newx][newy] != word.charAt(index)) {
                continue;
            }
            char tmp = board[newx][newy];
            board[newx][newy] = '0';
            flag = dfs(board, newx, newy, word, index + 1);
            board[newx][newy] = tmp;
            if (flag) {
                return flag;
            }
        }
        return flag;
    }

    @Test
    public void test() {
        char[][] board = new char[][]{{'a', 'a'}};
        String a = "aaa";
        if (exist(board, a)) {
            System.out.println("aaa");
        }
    }
}
