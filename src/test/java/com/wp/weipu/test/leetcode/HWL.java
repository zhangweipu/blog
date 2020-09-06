package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class HWL {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    public String notReCuPreOrder(TreeNode root) {
        // write code here
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return "";
        }
        stack.push(root);
        while (stack.size() > 0) {
            TreeNode tmp = stack.pop();
            sb.append(tmp.val);
            if (tmp.right != null) {
                //这题有误
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        int len = sb.length();
        return sb.toString();
    }

    public String tets(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        TreeNode tmp = root;
        while (tmp != null) {
            sb.append(tmp.val);
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                tmp = tmp.left;
            } else {
                if (!stack.isEmpty()) {
                    tmp = stack.pop();
                } else {
                    break;
                }
            }
        }
        int len = sb.length();
        return sb.toString().substring(0, len - 1);
    }

    /**
     * 无头节点的链表反转
     *
     * @param head
     * @return
     */
    public ListNode reserveList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    /**
     * 集齐三个可以换奖品，每两个可以换一种
     * 不知道能通过几个用例
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int numberofprize(int a, int b, int c) {
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        int sum = arr[0];
        for (int i = 0; i < 3; i++) {
            arr[i] -= sum;
        }
        int avg = (arr[1] + arr[2]) / 2;
        arr[1] = avg;
        arr[2] = avg;
        //这样不对啊
        while (arr[0] < arr[1]) {
            arr[1]--;
            arr[2]--;
            arr[0]++;
        }
        return sum + arr[0];
    }

    @Test
    public void test3() {
        System.out.println(numberofprize(0, 3, 100));

        int[] arr = {9, 4, 2};
        Arrays.sort(arr);
        int ans = arr[0];
        int b = arr[1] - arr[0], c = arr[2] - arr[0];
        int cb = c - b;
        if (b < cb / 2) {
            ans += b;
            //减去给a的，此时a=b
            cb = cb - 2 * b;
            //除以5是给别人两个自己留一个
            ans += cb / 5;
        } else {
            ans += cb / 2;
        }
        System.out.println(ans);
    }

    public int getNum(int w, int[] position) {
        //房子的个数
        int n = position.length / 2;
        //直接计算没这个好理解
        double[][] arr = new double[n][2];
        int j = 0;
        for (int i = 0; i < position.length; i += 2) {
            double length = position[i + 1] / 2.0;
            //长点心吧。。。。啊啊啊啊啊
            arr[j][0] = position[i] - length;
            arr[j][1] = position[i] + length;
            j++;
        }
        int ans = 2;
        for (int i = 1; i < n; i++) {
            double b = arr[i][0] - arr[i - 1][1];
            if (w < b) {
                ans += 2;
            } else if (w == b) {
                ans++;
            }
        }
        return ans;
    }

    @Test
    public void test4() {
        int[] arr = {-1, 4, 5, 2};
        System.out.println(getNum(2, arr));
    }

    public int binarySearch(int target, int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void test7() {
        int[] arr = {1, 3, 5, 6, 7};
        System.out.println(binarySearch(1, arr));
    }

    /**
     * 吃最少的糖，坏最多的牙
     * 等比数列
     *
     * @param n
     * @param m
     * @return
     */
    public int firstEat(int n, int m) {
        if (n == 1) {
            return m;
        }
        int num = (m / 3) * 2;
        for (int i = 1; i < num; i++) {

        }
        return 1;
    }

    public boolean isEnough(int n, int m) {
        return false;
    }
}
