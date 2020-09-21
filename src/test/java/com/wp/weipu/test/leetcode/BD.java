package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

public class BD {

    /**
     * 通过判断存在多少有序的，求需要移动的
     *
     * @param arr
     * @return
     */
    public int moveNum2(int[] arr) {
        int n = arr.length;
        int[] a = new int[n];
        System.arraycopy(arr, 0, a, 0, n);
        Arrays.sort(a);
        int count = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (a[j] == arr[i]) {
                count++;
                j++;
            }
        }
        return n - count;
    }

    /**
     * 这个思路就是错的
     *
     * @param list
     * @return
     */
    public int moveNum(List<Integer> list) {
        int n = list.size();
        int times = 0;
        while (true) {
            if (isOrder(list)) {
                break;
            }
            int max = Integer.MIN_VALUE;
            int index = n;
            //出口是哪个,会出现死循环，。。。todo：不对没办法判断有序
            for (int i = n - times - 1; i >= 0; i--) {
                if (max <= list.get(i)) {
                    max = list.get(i);
                    index = i;
                }
            }
            int a = list.remove(index);
            list.add(a);
            times++;
        }
        return times;
    }

    public boolean isOrder(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] n = new int[]{19, 18, 8, 25};
        List<Integer> list = new LinkedList<>();
        for (int m : n) {
            list.add(m);
        }
        System.out.println(moveNum(list));
        System.out.println(moveNum2(n));
    }

    public class DNode {
        public DNode next;
        public DNode pre;
        public int val;

        public DNode(int val) {
            this.val = val;
            this.next = null;
            this.pre = null;
        }

    }

    /**
     * 这是干啥的来着
     *
     * @param n
     * @param k
     * @return
     */
    public int maxTimes(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            //全部是大于号的时候
            dp[i][0] = 1;
        }
        //相当于枚举啊
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k && j < i; j++) {
                if (j == i - 1) {
                    dp[i][j] = 1;
                } else if (i > j - 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] * (i - j) + dp[i - 1][j - 1] * j;
                }
            }
        }
        return dp[n][k];
    }

    /**
     * 判断数字格式
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        //三个字段判断字符类型,todo:还要看这几个判断条件的排列组合啊啊啊啊。。
        boolean isNum = false, isSign = false, iseOrE = false;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= '0' && arr[i] <= '9') {
                isNum = true;
            } else if (arr[i] == '.') {
                if (isSign || iseOrE) {
                    return false;
                }
                isSign = true;
            } else if (arr[i] == 'e' || arr[i] == 'E') {
                if (!isNum || iseOrE) {
                    return false;
                }
                iseOrE = true;
                isNum = false;
                //这个地方有漏洞的
            } else if (arr[i] == '-') {
                if (i != 0 && arr[i - 1] != 'e' && arr[i] != 'E') {
                    return false;
                }
            } else if (arr[i] == '+') {
                if (i != 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return isNum;
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Collections.reverse(list);
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * 使用了三个set用来存放左斜和右斜  （0，0）和（3，3）
     * （1，5）和（0，4）通过和差判断是不是在一条写线上
     *
     * @param n
     * @return
     */
    //列
    Set<Integer> isCol = new HashSet<>();
    //左斜
    Set<Integer> diag1 = new HashSet<>();
    //右斜
    Set<Integer> diag2 = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();

        int[] arr = new int[n];
        //为什么不填充0
        Arrays.fill(arr, -1);
        for (int i = 0; i < n; i++) {
            backtrack(ans, arr, i, n);
        }
        return ans;
    }

    /**
     * arr使用一维数组保存每一行的坐标
     *
     * @param ans
     * @param arr
     * @param row
     * @param n
     */
    public void backtrack(List<List<String>> ans, int[] arr, int row, int n) {
        if (row == n) {
            List<String> rows = generate(arr, n);
            ans.add(rows);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isCol.contains(i)) {
                continue;
            }
            int dig1 = row - i;
            if (diag1.contains(dig1)) {
                continue;
            }
            int dif2 = row + i;
            if (diag2.contains(dif2)) {
                continue;
            }
            arr[row] = i;
            isCol.add(i);
            diag1.add(dig1);
            diag2.add(dif2);
            backtrack(ans, arr, row + 1, n);
            arr[row] = -1;
            isCol.remove(i);
            diag1.remove(dig1);
            diag2.remove(dif2);
        }
    }

    public List<String> generate(int[] arr, int n) {
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char[] c = new char[n];
            Arrays.fill(c, '.');
            c[arr[i]] = 'Q';
            ans.add(new String(c));
        }
        return ans;
    }

    @Test
    public void test8() {
//        int[] arr = new int[]{1, 2, 3, 4, 5};
//        System.arraycopy(arr, 4, arr, 3, 1);
//        List<Integer> lis = Collections.synchronizedList(new ArrayList<>());
//        System.out.println(JSON.toJSONString(arr));
        String a = "12";
        String b = "13";
        long a1 = Long.parseLong(a);
        long b1 = Long.parseLong(b);
        System.out.println(a1 + b1);

    }


}
