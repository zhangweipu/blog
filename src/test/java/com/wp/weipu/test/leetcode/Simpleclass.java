package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.LoadClass;
import com.wp.weipu.test.Json;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Executors;

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

    /**
     * 计算连续数的个数，组成一个列表。然后两两想比较，取最小的
     *
     * @param s
     * @return
     */
    public int countBinarySubstring(String s) {
        int n = s.length();
        List<Integer> list = new ArrayList<>();
        int i = 0;
        //还可以用一个last代替，存结果的
        while (i < n) {
            char c = s.charAt(i);
            int count = 0;
            while (i < n && s.charAt(i) == c) {
                ++i;
                ++count;
            }
            list.add(count);
        }
        int ans = 0;
        for (int j = 1; j < list.size(); j++) {
            ans += Math.min(list.get(i), list.get(i - 1));
        }
        return ans;
    }

    /**
     * 找到排序数组反转的点，同时这个点还要等于target
     * O(logn)
     * 二分查找试试
     * 我从两边开始找试试
     * 数组是两段升序，只要比较target的大小就可以确定在那一段了。。。。。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int i = 0, j = nums.length - 1;
        int flag = 0;
        while (i < j) {
            if (i + 1 < j && nums[i] > nums[i + 1]) {
                if (nums[i] == target) {
                    return i;
                } else if (nums[i + 1] == target) {
                    return i + 1;
                }
                flag = 1;
                break;
            }
            if (j - 1 > i && nums[j] < nums[j - 1]) {
                if (nums[j] == target) {
                    return j;
                } else if (nums[j - 1] == target) {
                    return j - 1;
                }
                flag = 2;
                break;
            }
            i++;
            j--;
        }

        //可能是有序的
        if (flag == 0) {
            if (nums[0] == target) {
                return 0;
            } else if (nums[nums.length - 1] == target) {
                return nums.length - 1;
            }
        }
        if (nums[i] == target) {
            return i;
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        int i = 0, j = len - 1;
        //在前半段
        if (nums[0] <= target) {
            while (i < len) {
                if (target == nums[i]) {
                    return i;
                }
                if (i < j && nums[i] > nums[i + 1]) {
                    break;
                }
                i++;
            }
        } else {
            while (j > 0) {
                if (target == nums[j]) {
                    return j;
                }
                if (j > 0 && nums[j] < nums[j - 1]) {
                    break;
                }
                j--;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search2(nums, 0));
    }

    /**
     * 遍历矩阵的问题
     *
     * @param board
     */
    //四个方向
//    int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void solve(char[][] board) {
        int col = board[0].length, row = board.length;
        if (col < 1 || row < 1) {
            return;
        }
        //游走索引
//        int i = 1, j = row - 2;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (board[i][j] == 'O') {
                    if (isX(board, i, j)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    /**
     * 使用递归的放法
     *
     * @param board
     * @param i
     * @param j
     * @return
     */
    public boolean isX(char[][] board, int i, int j) {
        int col = board[0].length, row = board.length;
        //任意一个到达边界
        if ((i == row - 1 || j == col - 1 || i == 0 || j == 0) && board[i][j] == 'O') {
            return false;
        }
        if (board[i][j] == 'X') {
            return true;
        }
        //先占了

        //向上
        if (isX(board, i + 1, j) && isX(board, i, j - 1) && isX(board, i, j + 1)) {
            board[i][j] = 'O';
            return true;
        } else {
            board[i][j] = 'O';
            return false;
        }
    }

    @Test
    public void test3() {
        char[][] nums = new char[][]{{'X', 'X', 'X'}, {'X', 'O', 'X'}, {'X', 'X', 'X'}};
        solve(nums);
        System.out.println(JSON.toJSONString(nums));
        if (false && false) {
            System.out.println("sss");
        }
    }

    /**
     * 先重边界开始查找
     *
     * @param board
     */
    public void solve1(char[][] board) {
        int row = board.length;
        if (row == 0) {
            return;
        }
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            dfs(board, i, 0);
            dfs(board, i, col - 1);
        }
        for (int j = 0; j < col; j++) {
            dfs(board, 0, j);
            dfs(board, row - 1, j);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        int row = board.length;
        int col = board[0].length;
        if (i < 0 || j < 0 || i >= row || j >= col || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    /**
     * 旋转数组，n*n的
     * 先上下翻转，再对角翻转
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int row = matrix.length;
        if (row == 1 || row == 0) {
            return;
        }
        int i = 0, j = row - 1;
        //上下翻转
        while (i < j) {
            for (int l = 0; l < row; l++) {
                int tmp = matrix[i][l];
                matrix[i][l] = matrix[j][l];
                matrix[j][l] = tmp;
            }
            i++;
            j--;
        }
        //对角翻转
        for (int m = 0; m < row; m++) {
            for (int n = m; n < row; n++) {
                int tmp = matrix[m][n];
                matrix[m][n] = matrix[n][m];
                matrix[n][m] = tmp;
            }
        }
    }

    /**
     * 四周旋转的办法
     *
     * @param matrix
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        //明明一样的旋转
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
//                int temp = matrix[n - 1 - j][i];
//                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
//                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
//                matrix[j][n - 1 - i] = matrix[i][j];
//                matrix[i][j] = temp;
                //我这么想是错的
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }

    }

    @Test
    public void test9() {
        int[][] mat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate1(mat);
        System.out.println(JSON.toJSONString(mat));
    }

    /**
     * 下一个最大的数
     * 从后往前遍历，然后交换
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        //虽然对了一些用例但是还是不正确
//        if (n == 0) {
//            return;
//        }
//        while (n > 0) {
//            n--;
//            if (n > 0 && nums[n - 1] < nums[n]) {
//                int tmp = nums[n - 1];
//                nums[n - 1] = nums[n];
//                nums[n] = tmp;
//                break;
//            }
//        }
//        if (n == 0) {
//            Arrays.sort(nums);
//        }
        int i = 0, j = n - 1;
        while (i < j) {
            if (j > 0 && nums[j] < nums[j - 1]) {
                j--;
            }
            if (i < n && nums[i] > nums[i + 1]) {
                i++;
            }
        }
        if (i == j) {
            Arrays.sort(nums);
            return;
        }
        //ij进行交换
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;

    }

    @Test
    public void test10() {
        int[] nums = new int[]{1, 3, 2};
        nextPermutation(nums);
        System.out.println(JSON.toJSONString(nums));
    }

    /**
     * 先遍历树，获取到一号玩家的父亲节点和孩子节点
     *
     * @param root
     * @param n
     * @param x
     * @return
     */
//    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
//
//    }
//    public int dfs(TreeNode root){
//
//    }

    /**
     * 获取可能的字符串
     * 怎么去重
     *
     * @return
     */
    public List<String> choose(int n, int m) {
        int[] colored = new int[n + 1];
//        int[] str = new int[m];
        String sb = new String();
        List<String> set = new ArrayList<>();
        setNum(set, colored, sb, -1, n, 0, m);
        Collections.sort(set);
        return set;
    }

    //不能用数组存

    /**
     * 传的还是引用，值改变了，就相当于重新创建了一个对象
     *
     * @param set
     * @param colored
     * @param sb
     * @param l
     * @param n
     * @param m
     * @param len
     */
    public void setNum(List<String> set, int[] colored, String sb, int l, int n, int m, int len) {
        //数组的length怎么算的
//        String sb = str;
        if (l != -1) {
            sb = sb + l;
        }
        if (sb.length() == len) {
            set.add(sb);
//            str[m - 1] = 0;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (colored[i] != 1 && i > l) {
                colored[i] = 1;
                setNum(set, colored, sb, i, n, m + 1, len);
                colored[i] = 0;
            }
        }
    }

    @Test
    public void test13() {
        List<String> s = choose(5, 2);
        Collections.sort(s);
        Iterator<String> i = s.iterator();
        while (i.hasNext()) {
            String dd = i.next();
            System.out.println(dd);
        }
        int[] ss = {1, 2, 3};
        System.out.println(ss.toString());
    }


    @Test
    public void getM() {
        int m = 4;
        int count = 0;
        while (m > 0) {
            m = m >> 1;
            count++;
        }
        System.out.println(count);
        //右边是移位的位数
        int l = 5 << 1;
        System.out.println(l);
    }

    public void getNum(int[][] arr) {
        int row = arr.length, col = arr[0].length;
        int c = 0, c1 = Math.min(row, col) / 2 + 1;
        System.out.println(row + "--" + col);
        int count = 0;
        int sum = row * col;
        while (c < c1) {
            //上从左到右
            for (int i = c; count <= sum && i < col - c; i++) {
                count++;
            }
            for (int i = c + 1; count <= sum && i < row - c; i++) {
                count++;
            }
            for (int i = col - c - 2; count <= sum && i >= c; i--) {
                count++;
            }
            for (int i = row - c - 2; count <= sum && i > c; i--) {
                count++;
                System.out.println(arr[i][c]);
            }
            c++;
        }
    }

    @Test
    public void test19() {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {2, 3, 4, 6}, {8, 6, 4, 3}, {1, 2, 3, 4}, {5, 6, 7, 8}};
        getNum(arr);
    }

    public void findNum(int[] arr, int begin, int end) {
        if (begin > end) {
            return;
        }
        int mid = (begin + end) / 2;
        if (mid - 1 >= begin && mid + 1 <= end) {
            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                findNum(arr, mid, end);
            } else if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                findNum(arr, begin, mid);
            } else {
                System.out.println(arr[mid]);
            }
        }
    }

    @Test
    public void test40() {
        int[] arr = new int[]{1, 2, 3, 5, 6, 7, 4, 7, 3, 2, 1};
        Integer e = new Integer(1);
        System.out.println(1 * 3 == 3);
    }

    /**
     * 合并区间。。。
     * 数组是排序完的吗
     * 需要全排列吗
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        //这个我咋就记不住呢
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int i = 1, len = intervals.length;
        List<int[]> list = new ArrayList<>();
        while (i <= len) {
            //进行合并
            int[] a = intervals[i - 1];
            int right = a[1];
            int left = a[0];
            while (i < len && right >= intervals[i][0]) {
                right = Math.max(intervals[i][1], right);
                i++;
            }
            list.add(new int[]{left, right});
            i++;
        }

        return list.toArray(new int[0][]);
    }

    @Test
    public void test91() {
        int[][] m = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        merge(m);

    }
}
