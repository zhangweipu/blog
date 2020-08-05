package com.wp.weipu.test.leetcode;

import java.util.*;

public class Solutions {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        int n = 5;
        int m = 2;
        int[] a = new int[]{1, 2, 1, 2, 3, 2};
//        for (int i = 0; i < n; i++) {
//            a[i] = sc.nextInt();
//        }
        int count = countNum(n, a, m);
        System.out.println(count);
    }

    public static int countNum(int n, int[] a, int m) {
        int count = 0;
        //存放个数

        Map<Integer, Integer> firstIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!firstIndex.containsKey(a[i])) {
                firstIndex.put(a[i], i);
            }
        }
        for (int i = 0; i < n; i++) {
            //存放初始位置
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                if (map.get(a[j]) != null && map.get(a[j]) >= m - 1) {
                    int index = firstIndex.get(a[j]);
                    count += index * (n - j) + 1;
                    firstIndex.put(a[j], j);
                } else {
                    map.merge(a[j], 1, Integer::sum);
                }
            }
        }
        return count;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 1;
        }
        return 1 + Math.max(maxDepth(root.right), maxDepth(root.left));
    }

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        //做的插入操作所以选linkedList
        List<List<Integer>> list = new LinkedList<>();
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> child = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                child.add(tmp.val);
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
            }
            list.add(child);
        }
        return list;
    }

}