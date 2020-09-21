package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetEasy {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    /**
     * 拆数
     *
     * @param num
     * @return
     */
    public int splitNum(int num) {
        if (num == 1) {
            return 0;
        }
        int sub = num - 2;
        int sum = 0;
        while (sub > 2) {
            sum++;
            sub -= 2;
        }
        if (isSu(sub)) {
            sum++;
        }
        return sum;
    }

    public boolean isSu(int num) {

        int i = 2;
        while (i * i <= num) {
            int j = num / i;
            if (i * j == num) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Test
    public void test() {
        int[] a = {7, 5, 39};
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += splitNum(a[i]);
        }
        System.out.println(sum);
        if (isSu(0)) {
            System.out.println("shi");
        }
    }

    public static int getNum(int[] a) {
        Arrays.sort(a);
        int i = a.length - 1;
        int sum1 = a[i--], sum2 = 0;
        int max = 0;
        int sum = sum1;
        while (i >= 0) {
            sum += a[i];
            if (sum1 > sum2) {
                sum2 += a[i--];
            } else {
                sum1 += a[i--];
            }
            if (sum1 == sum2) {
                max = 2 * sum1;
            }
        }

        return sum - max;
    }

    @Test
    public void test1() {
        int[] a = {5, 15, 30, 30, 60};
        int res = getNum(a);
        System.out.println(res);
    }

    public static String getBetween(String a, String b) {
        int aLen = a.length(), bLen = b.length();
        int len = Math.min(aLen, bLen);
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sb.append(a.charAt(i));
            } else if (a.charAt(i) + 1 < b.charAt(i)) {
                sb.append((char) (a.charAt(i) + 1));
            } else if (a.charAt(i) + 1 == b.charAt(i)) {
                sb.append(b.charAt(i));
                if (i < b.length() - 1) {
                    sb.append((char) (b.charAt(i + 1) - 1));
                }
            }
        }
        return sb.toString();
    }

    @Test
    public void test3() {
        String res = getBetween("aac", "abcb");
        System.out.println(res);
        System.out.println((char) ('a' + 1));


    }

    class Node {
        int id;
        int left;
        int right;
    }

    public int getYingTao(int n, int m, String[][] str) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (map.get(i) == null) {
                map.put(i, new Node());
            }
            if (str[i][1].equals("left")) {
                map.get(i).left = Integer.parseInt(str[i][2]);
            } else {
                map.get(i).right = Integer.parseInt(str[i][2]);
            }
        }
        int sum = 0;
        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            if (map.get(entry.getValue().left) == null && map.get(entry.getValue().right) == null) {
                sum++;
            }
        }
        return sum;
    }

    public String getStr(String s) {
        Pattern p = Pattern.compile("\\w+");
        Matcher mt = p.matcher(s);
        String b = mt.group();
        System.out.println(b);
        return null;
    }

    @Test
    public void test9() {
        getStr("aa");
    }

    public int maxSubarraySumCircular(int[] a) {
        int len = a.length;
        int n = 2 * len;
        int[] nums = new int[n];
        for (int i = 0; i < len; i++) {
            nums[i] = a[i];
            nums[i + len] = a[i];
        }
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Deque<Integer> queue = new ArrayDeque<>();
        //前零个数和为0
        queue.addLast(0);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (queue.size() > 0 && queue.peekFirst() < i - len) {
                //i到第一个的距离，要在len之内
                queue.pollFirst();
            }
            if (queue.size() > 0) {
                res = Math.max(res, sum[i] - sum[queue.peekFirst()]);
            }
            //如果前面的大就去掉啊。。
            while (queue.size() > 0 && sum[queue.peekLast()] >= sum[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        return res;
    }

    /**
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int minI = 0, minJ = 0, maxI = 0, maxJ = 0;
        dp[0][0] = matrix[0][0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < m; i++) {
            int ma = dp[0][i] + dp[0][i - 1];
            if (ma < dp[0][i]) {
                if (max < dp[0][i]) {
                    minI = 0;
                    minJ = i;
                    max = dp[0][i];
                }
            } else {
                dp[0][i] = ma;
                if (max < dp[0][1]) {
                    maxI = 0;
                    maxJ = i;
                    max = dp[0][i];
                }
            }
        }
        for (int i = 1; i < n; i++) {
            int ma = dp[i][0] + dp[i - 1][0];
            if (ma < dp[i][0]) {
                if (max < dp[i][0]) {
                    minI = i;
                    minJ = 0;
                    max = dp[i][0];
                }
            } else {
                dp[i][0] = ma;
                if (max < dp[i][0]) {
                    maxI = i;
                    maxJ = 0;
                    max = dp[i][0];
                }

            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int ma = dp[i][j] + dp[i - 1][j] + dp[i][j - 1];
                if (ma < dp[i][0]) {
                    if (max < dp[i][0]) {
                        minI = i;
                        minJ = 0;
                        max = dp[i][0];
                    }
                } else {
                    dp[i][0] = ma;
                    if (max < dp[i][0]) {
                        maxI = i;
                        maxJ = 0;
                        max = dp[i][0];
                    }

                }
            }
        }
        return null;
    }
}
