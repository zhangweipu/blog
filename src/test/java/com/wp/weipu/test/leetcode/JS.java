package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class JS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num = 2 * (n + 1) - 1;
        int[][] mat = new int[n + 1][num + 1];
        for (int j = 1; j < n + 1; j++) {
            mat[j][1] = 1;
            mat[j][2 * j - 1] = 1;
        }
        System.out.println(1);
        for (int i = 2; i < n + 1; i++) {
            System.out.print(1 + " ");
            for (int j = 2; j < 2 * i - 1; j++) {
                if (j <= i) {
                    mat[i][j] = mat[i - 1][j - 1] + mat[i - 1][j - 2];
                }
                if (j > i) {
                    mat[i][j] = mat[i - 1][j] + mat[i - 1][j - 1];
                }
                System.out.print(mat[i][j] + " ");
            }
            System.out.println(mat[i][2 * i - 1]);
        }
    }

    public JSTree buildTree(int[] arr) {
        int n = arr.length;
        JSTree root = new JSTree(arr[0]);
        Stack<JSTree> stack = new Stack<>();
        stack.push(root);
        for (int i = 1; i < n - 2; i++) {
            //直接跳过去
            if (arr[i] == -1 && arr[i + 1] == -1) {
                i++;
                stack.pop();
                continue;
            }
            JSTree tmp = stack.peek();
            if (tmp.left == null && arr[i] != -1) {
                tmp.left = new JSTree(arr[i]);
                stack.push(tmp.left);
                continue;
            }
            if (tmp.right == null && arr[i + 1] != -1) {
                tmp.right = new JSTree(arr[i + 1]);
                stack.push(tmp.right);
            }
        }
        return root;
    }

    @Test
    public void test() {
        int[] arr = {3, 5, 6, -1, -1, 2, 7, -1, -1, 4, -1, -1, 1, 9, -1, -1, 8, -1, -1};
        List<Integer> lis = new LinkedList<>();


    }

    class JSTree {
        int val;
        JSTree left;
        JSTree right;

        public JSTree(int val) {
            this.val = val;
        }

        public JSTree() {

        }
    }

    @Test
    public void test6() {
        String[] s1 = new String[]{"1101", "1010", "1111", "1110"};
        String[] s2 = new String[]{"ABCD", "EFGH", "IJKL", "MNPQ"};
        rotatePassword(s1, s2);
    }

    public String rotatePassword(String[] s1, String[] s2) {
        // write code here
        int n = s1.length;
        StringBuilder sb = new StringBuilder();
        char[][] mima = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mima[i][j] = s1[i].charAt(j);
            }
        }
        appendStr(sb, mima, s2);
        char[][] two = computeIndex(mima);
        appendStr(sb, two, s2);
        char[][] three = computeIndex(two);
        appendStr(sb, three, s2);
        char[][] four = computeIndex(three);
        appendStr(sb, four, s2);
        return sb.toString();
    }

    public void appendStr(StringBuilder sb, char[][] mat, String[] a2) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == '0') {
                    sb.append(a2[i].charAt(j));
                }
            }
        }
    }

    //计算坐标的旋转
    public char[][] computeIndex(char[][] arr) {
        int n = arr.length;
        char[][] mat = new char[n][n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = arr[j][i];
            }
        }
        return mat;
    }


    public Interval solve(int n, int k, String str1, String str2) {
        // write code here
        Interval inter = new Interval();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                sum++;
            }
        }
        if (sum <= k) {
            inter.start = sum;
        }
        inter.end = sum + (n - k);
        return inter;
    }

    @Test
    public void test3() {
        Interval aa = solve(3, 3, "ABC", "ABC");
        System.out.println(aa.start);
        System.out.println(aa.end);
    }

    public class Interval {
        int start;
        int end;
    }

    public static int isPlindRome(int num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            if ((num & 1) == 1) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            num >>>= 1;
        }
        if (sb.toString().equals(sb.reverse().toString())) {
            return 1;
        } else {
            return 0;
        }
    }

    @Test
    public void test8() {
        int[] nums = new int[]{6, 2, 3, 3, 3, 4};
        System.out.println(canBeEqual(nums));
    }

    /**
     * 给定6个人了
     *
     * @param nums
     * @return
     */
    public int canBeEqual(int[] nums) {
        int n = nums.length;
//        int total = Arrays.stream(nums).sum();
        int total=0;
        for (int k:nums){
            total+=k;
        }
        if (total % 2 != 0) {
            return 0;
        }
        Arrays.sort(nums);
        int target = total / 2;
        int i = 0, j = n - 1;
        while (i < j) {
            int k = i + 1;
            int sub = target - nums[i] - nums[j];
            if (sub < 0) {
                j--;
                continue;
            }
            while (k < j && sub > nums[k]) {
                k++;
            }
            if (sub == nums[k]) {
                return 1;
            }
            i++;
        }
        return 0;
    }

    public double xiangNong(String str) {
        Map<Character, Double> map = new HashMap<>();
        int n = str.length();
        for (int i = 0; i < str.length(); i++) {
            map.merge(str.charAt(i), 1.0, Double::sum);
        }
        double ans = 0;
        for (Map.Entry<Character, Double> entry : map.entrySet()) {
            double l = entry.getValue() / n;
            ans += Math.log(l)/Math.log(2) * l;
        }
        //小数保留。。。
        BigDecimal bg = new BigDecimal(ans).setScale(2, RoundingMode.DOWN);
        return -bg.doubleValue();
    }

    @Test
    public void test7() {
        System.out.println(xiangNong("AABB"));
    }


}
