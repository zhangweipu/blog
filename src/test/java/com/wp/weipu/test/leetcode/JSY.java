package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class JSY {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        List<Integer> ls = new ArrayList<>();
//        for (int i = n; i < m; i++) {
//            if (isPrime(i)) {
//                ls.add(i);
//                System.out.println(i);
//            }
//        }
//        int sum = 0;
//        for (int f : ls) {
//            String str = String.valueOf(f);
//            String s = str.substring(1, str.length() - 1);
//            int l = Integer.parseInt(s);
//
//            if (isPrime(l)) {
//                sum++;
//                System.out.println(l);
//            }
//        }
//        System.out.println(sum);
//    }

    public static boolean isPrime(int n) {
        if (n == 0 || n == 1) {
            return false;
        }
        int m = (int) Math.sqrt(n);
        while (m > 1) {
            if (n % m == 0) {
                return false;
            }
            m--;
        }
        return true;
    }

    public void pre(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            System.out.println(tmp.val);
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
    }

    public void pre2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                System.out.println(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.add(root);
        TreeNode cur = null;
        while (!queue.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                res.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            ans.add(res);
        }
        return ans;
    }

    /**
     * 使用头插入进行的
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            ans.addFirst(tmp.val);
            if (tmp.left != null) stack.push(tmp.left);
            if (tmp.right != null) stack.push(tmp.right);
        }
        return ans;
    }

    class Node {
        TreeNode root;
        int col;

        public Node(TreeNode root, int col) {
            this.root = root;
            this.col = col;
        }
    }

    /**
     * 层次遍历，记录每个节点的列位置
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        queue.add(new Node(root, 0));
        while (queue != null) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (map.get(cur.col) == null) {
                    List<Integer> a = new ArrayList<>();
                    a.add(cur.root.val);
                    map.put(cur.col, a);
                } else {
                    map.get(cur.col).add(cur.root.val);
                }
                if (cur.root.left != null) {
                    queue.add(new Node(cur.root.left, cur.col - 1));
                }
                if (cur.root.right != null) {
                    queue.add(new Node(cur.root.right, cur.col + 1));
                }
            }
        }
        //数的宽
        int[] ind = new int[map.size()];
        int i = 0;
        for (int k : map.keySet()) {
            ind[i] = k;
            i++;
        }
        Arrays.sort(ind);
        for (int j = 0; j < ind.length; j++) {
            ans.add(map.get(ind[j]));
        }
        return ans;
    }

    public List<Integer> getArr(int[] arr, int k) {
        int n = arr.length;
//      int min = Integer.MAX_VALUE;
        List<Integer> ls = new ArrayList<>();
        if (n < k) {
            OptionalInt min = Arrays.stream(arr).min();
            ls.add(min.getAsInt());
            return ls;
        }
        for (int i = 0; i <= n - k; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < i + k; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                }
            }
            ls.add(min);
        }
        return ls;
    }

    @Test
    public void test9() {
        int[] arr = new int[]{1, 2, 6, 2, 9, 6, 1, 8};
        List<Integer> ls = getArr(arr, 3);
        for (int i : ls) {
            System.out.print(i + " ");
        }
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int k = in.nextInt();
//        String str = in.next();
//
//        String[] nums = str.split(" ");
//        int[] arr = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            arr[i] = Integer.valueOf(nums[i]);
//        }
//        List<Integer> ls = getArr(arr, k);
//        for (int i : ls) {
//            System.out.print(i + " ");
//        }
//    }

    /**
     * 需要一种排序
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str, (a, b) ->
                (a + b).compareTo(b + a)
        );
        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            sb.append(s);
        }
        return sb.toString();
    }
}
