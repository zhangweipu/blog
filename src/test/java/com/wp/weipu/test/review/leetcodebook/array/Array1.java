package com.wp.weipu.test.review.leetcodebook.array;

import com.wp.weipu.test.leetcode.LNode;
import org.junit.Test;

import java.util.*;

public class Array1 {
    /**
     * o(n)的时间复杂度
     * 使用map记录访问过的值
     *
     * @param array
     * @param target
     * @return
     */
    public int[] twoSum(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int sub = target - array[i];
            if (map.containsKey(sub)) {
                return new int[]{array[i], sub};
            }
            map.put(array[i], i);
        }
        return null;
    }

    /**
     * 寻找中位数，时间复杂度在o(log(n+m))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        //交换数组位置，还不用判断这样挺好的
        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0, high = nums1.length, k = (n + m) >> 1;
        int midNums1 = 0, midNums2 = 0;
        while (low < high) {
            midNums1 = low + (low + high) >> 1;
            midNums2 = k - midNums1;
            //判断中位数的关键在这里
            //nums1的分界线画多了，向左移动
            if (midNums1 > 0 && nums1[midNums1 - 1] > nums2[midNums2]) {
                high = midNums1 - 1;
                //nums1的分界线画少了，向右移
            } else if (midNums1 != n && nums1[midNums1] < nums2[midNums2 - 1]) {
                low = midNums1 + 1;
            } else {
                //找到合适的划分了，跳出
                break;
            }
        }
        //长度是奇数，偶数两种情况
        int midLeft = 0, midRight = 0;
        if (midNums1 == 0) {
            midLeft = nums2[midNums2 - 1];
        } else if (midNums2 == 0) {
            midLeft = nums1[midNums1 - 1];
        } else {
            midLeft = Math.max(nums1[midNums1 - 1], nums2[midNums2 - 1]);
        }
        //奇数情况
        if (((n + m) & 1) == 1) {
            return midLeft;
        }
        if (midNums1 == n) {
            midRight = nums2[midNums2];
        } else if (midNums2 == m) {
            midRight = nums1[midNums1];
        } else {
            midRight = Math.min(nums1[midNums1], nums2[midNums2]);
        }
        return (midLeft + midRight) >> 1;
    }

    /**
     * 原地反转链表
     *
     * @param head
     * @return
     */
    public LNode reverseList(LNode head) {
        LNode cur = head, pre = null;
        while (cur != null) {
            LNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 131. 分割回文串
     * 使用深度遍历 加 动态规划的方式
     *
     * @param s
     * @return
     */
    List<List<String>> ret = new ArrayList<>();
    boolean[][] f;
    List<String> ans = new ArrayList<String>();

    public List<List<String>> partition(String s) {
        int n = s.length();
        //先用动态规划求回文串
        f = new boolean[n][n];
        for (boolean[] ff : f) {
            Arrays.fill(ff, true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1];
            }
        }
        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == s.length()) {
            ret.add(new ArrayList<>(ans));
        }
        for (int j = i; j < s.length(); j++) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    /**
     * 856. 括号的分数
     *
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        //第0层
        stack.push(0);
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int m = stack.pop();
                stack.push(m + Math.max(2 * v, 1));
            }
        }
        return stack.pop();
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     *
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int top = -1;
        for (char c : S.toCharArray()) {
            if (top >= 0 && sb.charAt(top) == c) {
                sb.deleteCharAt(top);
                top--;
            } else {
                sb.append(c);
                top++;
            }
        }
        return sb.toString();
    }

    @Test
    public void test1() {
        String aa = "abbaca";
        System.out.println(removeDuplicates(aa));
    }

    /**
     * 227. 基本计算器 II
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            //不全是个位数
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (!Character.isDigit(cur) && cur != ' ' || i == s.length() - 1) {
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (preSign == '/') {
                    stack.push(stack.pop() / num);
                }
                preSign = cur;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    /**
     * LCP 22. 黑白方格画
     *
     * @param n
     * @param k
     * @return
     */
    public int paintingPlan(int n, int k) {
        if (k < n) {
            return 0;
        }
        if (k == n * n) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i * n + j * (n - i) == k) {
                    res += combine(i, n) * combine(j, n);
                }
            }
        }
        return res;
    }

    /**
     * 排列组合的计算
     *
     * @param m
     * @param n
     * @return
     */
    public int combine(int m, int n) {
        if (m == 0) {
            return 1;
        }
        int x = 1, y = 1, z = n - m + 1;
        while (n >= z) {
            x *= n;
            n--;
        }
        while (m >= 1) {
            y *= m;
            m--;
        }
        return x / y;
    }
}
