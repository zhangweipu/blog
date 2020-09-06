package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

/**
 *
 */
public class ChangeListToBST {
    /**
     * 需要旋转的吗
     * 中间的节点做根
     * 怎么找到中点啊啊
     * 好像不用左旋右旋的
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        //找到中间节点就行了
        ListNode fast = head, slow = head, pre = slow;
        //如果链表只有两个的情况。。。
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre == slow) {
            slow = slow.next;
        }
        pre.next = null;

        TreeNode root = new TreeNode(slow.val);
        root.right = sortedListToBST(slow.next);
        root.left = sortedListToBST(head);
        return root;
    }

    /**
     * 上面那个我没找到错在哪了
     *
     * @return
     */
    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == null) {
            return null;
        }
        if (left == right) {
            return new TreeNode(left.val);
        }
        ListNode mid = getMid(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.right = buildTree(mid.next, right);
        root.left = buildTree(left, mid);
        return root;
    }

    public ListNode getMid(ListNode left, ListNode right) {
        ListNode slow = left, fast = right;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    @Test
    public void test() {
        ListNode node = new ListNode(-100), pre = node;
        int[] arr = new int[]{-10, -3, 0, 5, 9};
        for (Integer i : arr) {
            node.next = new ListNode(i);
            node = node.next;
        }
        System.out.println(pre);
        TreeNode t = sortedListToBST(pre.next);
        System.out.println(JSON.toJSONString(t));
    }

    /**
     * 左旋
     *
     * @param root
     */
    public TreeNode left(TreeNode root) {
        TreeNode tmp = root.right;
        root.right = tmp.left;
        tmp.left = root;
        return tmp;
    }

    /**
     * 右旋
     * 应该不会有问题的吧
     *
     * @param root
     * @return
     */
    public TreeNode right(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = tmp.right;
        tmp.right = root;
        return tmp;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Arrays.sort(candidates);

        getCombination(res, candidates, stack, 0, target);


        return res;
    }

    public void getCombination(List<List<Integer>> res, int[] candidates, Stack<Integer> stack, int index,
                               int target) {
        if (target == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            //怎么去重的
            // 小剪枝
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (target >= candidates[i]) {
                stack.push(candidates[i]);
                getCombination(res, candidates, stack, i + 1, target - candidates[i]);
                stack.pop();
            }
        }
    }

    @Test
    public void test2() {
        int[] arr = new int[]{10, 1, 2, 7, 6, 1, 5};
        System.out.println(JSON.toJSONString(combinationSum2(arr, 8)));
    }

    /**
     * 是否可以为数独数组
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board[i][i] != ',' && !set.contains(board)) {
                set.add(board[i][i]);
            } else {
                return false;
            }
        }
        return true;
    }
}
