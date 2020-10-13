package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.data.redis.connection.ReactiveHashCommands;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class T58 {
    /**
     * 使用栈实现吧
     *
     * @param input
     * @return
     */
    public int calculate(String input) {
        // write code here
        Stack<Integer> num = new Stack<>();
        char lastOp = '+';
        char[] ops = input.toCharArray();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i] == ' ') {
                continue;
            }
            if (Character.isDigit(ops[i])) {
                int tempNum = ops[i] - '0';
                while (++i < ops.length && Character.isDigit(ops[i])) {
                    tempNum = tempNum * 10 + (ops[i] - '0');
                }
                i--;
                if (lastOp == '+') {
                    num.push(tempNum);
                } else if (lastOp == '-') {
                    num.push(-tempNum);
                } else {
                    num.push(res(lastOp, num.pop(), tempNum));
                }
            } else {
                lastOp = ops[i];
            }

        }
        int ans = 0;
        for (int n : num) {
            ans += n;
        }
        return ans;
    }

    public int res(char op, int a, int b) {
        if (op == '*') return a * b;
        else if (op == '/') return a / b;
        else if (op == '+') return a + b;
        else
            return a - b;
    }

    public ArrayList<Integer> mergerArrays(ArrayList<Integer> arrayA, ArrayList<Integer> arrayB) {
        // write code here
        Collections.sort(arrayA);
        Collections.sort(arrayB);
        Map<Integer, Integer> map = new HashMap<>();
        for (int s : arrayA) {
            map.merge(s, 1, Integer::sum);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int b : arrayB) {
            if (map.getOrDefault(b, 0) > 0) {
                list.add(b);
            }
        }
        return list;
    }

    public int cal(String str) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> opt = new Stack<>();
        int i = 0;
        Map<Character, Integer> prior = new HashMap<>();
        prior.put('(', 0);
        prior.put(')', 0);
        prior.put('+', 1);
        prior.put('-', 1);
        prior.put('*', 2);
        prior.put('/', 2);
        int n = str.length();
        char[] arr = str.toCharArray();
        while (i < n) {
            if (arr[i] == ' ') {
                i++;
                continue;
            }
            int temp = 0;
            if (Character.isDigit(arr[i])) {
                temp = arr[i] - '0';
                while (++i < n && Character.isDigit(arr[i])) {
                    temp = temp * 10 + (arr[i] - '0');
                }
                i--;
                //进数
                num.push(temp);
            } else if (arr[i] == '(') {
                opt.push(arr[i]);
            } else if (arr[i] == ')') {
                //计算括号里面的
                while (opt.lastElement() != '(') {
                    char op = opt.pop();
                    int a = num.pop();
                    int b = num.pop();
                    //结果进栈
                    num.push(res(op, a, b));
                }
                opt.pop();
            } else {
                //谁的优先级高
                while (!opt.isEmpty() && prior.get(opt.lastElement()) >= prior.get(arr[i])) {
                    char op = opt.pop();
                    int a = num.pop();
                    int b = num.pop();
                    num.push(res(op, a, b));
                }
                opt.push(arr[i]);
            }
            i++;
        }
        int sum = 0;
        while (!num.isEmpty()) {
            sum += num.pop();
        }
        return sum;
    }

    /**
     * 只能向右和向下，这样就可以用动态规划去做了
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //只用grid就行
//        int[][] dp=
        //沿着行走
        for (int i = 1; i < m; i++) {
            grid[0][i] = grid[0][i] + grid[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            grid[i][0] = grid[i][0] + grid[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[n - 1][m - 1];
    }

    /**
     * 十进制转16进制
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        //倒着添加
        StringBuilder sb = new StringBuilder();
        char[] hex = "0123456789abcdef".toCharArray();
        while (num != 0) {
            int end = num & 15;
            sb.append(hex[end]);
            num >>>= 4;
        }
        if (sb.length() == 0) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }

    /**
     * 转化成8进制
     *
     * @param num
     * @return
     */
    public String toEight(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        while (num != 0) {
            int n = num & 7;
            sb.append(n);
            num >>>= 3;
        }
        return sb.reverse().toString();
    }

    @Test
    public void test() {
        System.out.println(toEight(16));
        System.out.println(8 % 7);
        System.out.println(8 & 7);
    }

    /**
     * 十六机制转十进制
     *
     * @param hex
     * @return
     */
    public int toDex(String hex) {
        int n = hex.length();
        int num = 0;
        for (int i = 0; i < n; i++) {
            num = num * 16 + charToInt(hex.charAt(i));
        }
        return num;
    }

    public int charToInt(char c) {
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        } else {
            return c - '0';
        }
    }

    public String ansHuiWen(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        //i,j表示这段
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int i = 0; i < n; i++) {
            String s1 = fingHuiWen(str, i, i);
            String s2 = fingHuiWen(str, i, i + 1);
            if (s1.length() >= s2.length()) {
                if (ans.length() < s1.length()) {
                    ans = s1;
                }
            } else {
                if (ans.length() < s2.length()) {
                    ans = s2;
                }
            }
        }
        return ans;
    }

    public String fingHuiWen(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    @Test
    public void test3() {
        System.out.println(ansHuiWen("cbbd"));
    }

    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> ls = new ArrayList<>();
        if (root == null) {
            return new int[0];
        }
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            ls.add(tmp.val);
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
        int[] arr = new int[ls.size()];
        int i = 0;
        for (int j : ls) {
            arr[i] = j;
            i++;
        }
        return arr;
    }

    /**
     * 二叉树转化成双链表
     * 我觉得和我刚刚写的没有区别啊。。。
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node pre = null, head = null, tail = null;
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            tail = current;
            if (pre == null) {
                head = current;
            } else {
                pre.right = current;
                current.left = pre;
            }
            pre = current;
            current = current.right;
        }
        tail.right = head;
        head.left = tail;
        return head;
    }

    @Test
    public void test4() {
        List<Integer> te = new ArrayList<>();
        te.add(1);
        te.add(2);
        te.add(3);
        //还是用for赋值吧
        //int[] re=te.toArray(new int[0]);
    }

    public int[] merge(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int[] ans = new int[n + m];
        int i = 0, j = 0;
        int index = 0;
        while (i < n && j < m) {
            if (a[i] < b[j]) {
                ans[index++] = a[i++];
            } else {
                ans[index++] = b[j++];
            }
        }
//        index--;
        //索引不要变啊啊啊啊啊啊啊。。。。。。
        if (i < n) {
            for (int k = i; k < n; k++) {
                ans[index++] = a[k];
            }
        }
        if (j < m) {
            for (int k = j; k < m; k++) {
                ans[index++] = b[k];
            }
        }
        return ans;
    }

    @Test
    public void test6() {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{1, 2, 4};
        int[] ans=merge(a,b);
        System.out.println(JSON.toJSONString(ans));
        Set<String> aa=new HashSet<>();

    }

}
