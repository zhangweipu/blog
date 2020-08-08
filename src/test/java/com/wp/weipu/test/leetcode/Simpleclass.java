package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.LoadClass;
import org.junit.Test;

import java.util.*;

/**
 * leetcode中简单的题
 */
public class Simpleclass {
    /**
     * 二进制运算
     * 输入: a = "11", b = "1"
     * 输出: "100"
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int len = Math.min(aLen, bLen);
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        //进位
        char tmp = '0';
        //怎么进位
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= len; i++) {
            //想等的情况
            if (aChar[aLen - i] == bChar[bLen - i]) {
                if (tmp == '0') {
                    sb.append('0');
                    if (aChar[aLen - i] == '1') {
                        tmp = '1';
                    }
                } else if (aChar[aLen - i] == '0') {
                    sb.append('1');
                    tmp = '0';
                } else {
                    sb.append('1');
                    tmp = '1';
                }
            } else {
                if (tmp == '0') {
                    sb.append('1');
                } else {
                    sb.append('0');
                    tmp = '1';
                }
            }

        }
        char[] aa = len == aLen ? bChar : aChar;
        for (int i = aa.length - 1 - len; i >= 0; i--) {
            if (aa[i] == tmp) {
                sb.append('0');
            } else {
                sb.append('1');
                tmp = '0';
            }
        }
        if (tmp == '1') {
            sb.append(tmp);
        }
        sb.reverse();
        return sb.toString();
    }

    @Test
    public void test1() {
        String res = addBinary("101", "10");
        System.out.println(res);
    }

    /**
     * 明天在刷
     * 排序 先固定俩，再双指针
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }

    /**
     * 外观数列
     * 对上一个数的描述
     * 使用递归算法也可以
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        StringBuilder sb = new StringBuilder("11");
        //1另算
        for (int i = 0; i < n - 2; i++) {
            int len = sb.length();
            int index = 0;
            StringBuilder tmp = new StringBuilder();
            while (index < len) {
                int num = 1;
                while (index < len - 1 && sb.charAt(index) == sb.charAt(index + 1)) {
                    num++;
                    index++;
                }
                tmp.append(num).append(sb.charAt(index));
                index++;
            }
            sb = tmp;
        }
        return sb.toString();
    }

    @Test
    public void test2() {
        String res = countAndSay(5);
        System.out.println(res);
    }

    /**
     * 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
        }
        return sum;
    }

    @Test
    public void test5() {
        int num = 886;
        //分解出百分位
        int h = num / 100;
        //十分位
        int t = num % 100 / 10;
        //个分位
        int g = num % 10;
        System.out.println(h + "," + t + "," + g);
        flowers(1000);
    }

    /**
     * 输出100-n的水仙花数，100<n<1000
     *
     * @param n
     */
    public void flowers(int n) {
        for (int i = 100; i < n; i++) {
            int h = i / 100;
            int t = i / 10 % 10;
            int g = i % 10;
            if ((h * h * h + t * t * t + g * g * g) == i) {
                System.out.println(h + "," + t + "," + g + "\n");
            }
        }
    }

    /**
     * 有序数组，两数之和
     * 使用双指针
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int i = 0, j = len - 1;
        while (i < j) {
            while (i < j && numbers[i] + numbers[j] < target) {
                i++;
            }
            while (i < j && numbers[j] + numbers[i] > target) {
                j--;
            }
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }

    /**
     * 最后一个单词的长度
     * 两个条件，一个是空格一个是count不等于0啊
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        s = "    " + s;
        int sLen = s.length() - 2;
        int max = 0;
        while (sLen >= 0) {
            if (s.charAt(sLen + 1) != ' ' && s.charAt(sLen) == ' ') {
                max++;
                break;
            }
            if (s.charAt(sLen + 1) != ' ') {
                max++;
            }
            sLen--;
        }
        return max;
    }

    @Test
    public void tes7() {
        System.out.println(mySqrt(1));
    }

    /**
     * 这个考虑用位运算
     * 使用二分法查找
     * 我这样更准确些
     * 题目要求不是这样啊
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        double mid = x / 2.0;
        double low = 0, high = x;
        while (low < high) {
//            if (mid * (mid + 1) > x && (mid - 1) * mid < x) {
//                break;
//            }
            if (mid * mid > x) {
                high = mid - 1;
            } else {
                low = mid;
            }
            mid = (high + 1 + low) / 2.0;
        }
        return (int) Math.floor(mid);
    }

    /**
     * 判断二叉树是否为平衡二叉树
     * 错了
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getHeight(root.right) - getHeight(root.left)) > 1) {
            return false;
        }
        return isBalanced(root.right) && isBalanced(root.left);
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = getHeight(root.left) + 1;
        int right = getHeight(root.right) + 1;
        return Math.max(left, right);
    }

    /**
     * 采用空间换时间的办法
     * 不带头节点
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> keySet = new HashSet<>();
        keySet.add(head.val);
        ListNode h = head.next, tmp = head;
        while (h != null) {
            if (keySet.contains(h.val)) {
                h = h.next;
                tmp.next = h;
            } else {
                keySet.add(h.val);
                h = h.next;
                tmp = tmp.next;
            }
        }
        return head;
    }

    /**
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (q == null && p != null) || (p.val != q.val)) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        int num = N;

        while (num > 0) {

        }
        return false;
    }

    /**
     * 相交链表
     * 找到相交的点
     * hash表法，不错
     * 它们要同时走
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode preA = headA, preB = headB;
        //同时才会有效
        while (preA != preB) {
            preA = preA == null ? headB : preA.next;
            preB = preB == null ? headA : preB.next;
        }
        return preA;
    }

    /**
     * 杨辉三角，
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();

        int[][] mat = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            mat[i][0] = 1;
            mat[i][i] = 1;
        }
        List<Integer> sub = new ArrayList<>();
        sub.add(1);
        res.add(sub);
        if (numRows == 1) {
            return res;
        }
        sub = new ArrayList<>();
        sub.add(1);
        sub.add(1);
        res.add(sub);
        if (numRows == 2) {
            return res;
        }
        for (int i = 2; i < numRows; i++) {
            sub = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    sub.add(mat[i][j]);
                } else {
                    mat[i][j] = mat[i - 1][j] + mat[i - 1][j - 1];
                    sub.add(mat[i][j]);
                }
            }
            res.add(sub);
        }
        return res;
    }

    @Test
    public void test4() {
        List<List<Integer>> res = generate(1);
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void test6() {
        String str = "true and false";
        String[] words = str.split(" ");
        Map<String, Boolean> keyMap = new HashMap<>();
        keyMap.put("true", true);

    }

    /**
     * 重建一棵二叉树
     * 其中的一个节点放错位置了
     * <p>
     * 使用中序遍历
     */
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;
        stack.add(root);
        while (stack.size() != 0) {
            TreeNode tmp = stack.peek();
            while (tmp.left != null) {
                stack.add(tmp.left);
                tmp = tmp.left;
            }
            tmp = stack.pop();
            if (pred != null && tmp.val < pred.val) {
                y = tmp;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = tmp;
            if (tmp.right != null) {
                stack.add(tmp.right);
            }
        }
        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //把这个也有container方法忘了
        Set<String> wordSet = new HashSet<>(wordList);
        //判断词是否在列表里
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        int wordLen = beginWord.length();
        //移除开头单词
        wordSet.remove(beginWord);
        //广度遍历的时候用
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int step = 1;
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            //依次遍历当前队列的单词
            for (int i = 0; i < currentSize; i++) {
                String word = queue.poll();
                char[] charArray = word.toCharArray();
                for (int j = 0; j < wordLen; j++) {
                    //替换每个位置的字符
                    char originChar = charArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originChar) {
                            continue;
                        }
                        charArray[j] = k;
                        //这也不叫建图啊
                        String nextWord = String.valueOf(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(nextWord)) {
                                queue.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                        charArray[j] = originChar;
                    }
                }
            }
            step++;
        }
        return 0;
    }
}
