package com.wp.weipu.test.leetcode;


public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode arrayToTree(int[] tree, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode();
        int mid = (end + start + 1) / 2;
        root.val = tree[mid];
        root.left = arrayToTree(tree, start, mid - 1);
        root.right = arrayToTree(tree, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        TreeNode root = arrayToTree(arr, 0, arr.length - 1);
        System.out.println(root);
    }
}
