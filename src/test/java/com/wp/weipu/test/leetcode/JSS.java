package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class JSS {


    public static int getNum(int[] nums, int n) {
        if (n > nums.length) {
            return 0;
        }
        int res = nums[n - 1];
        int left = 2 * n;
        int right = 2 * n + 1;
        res += getNum(nums, left);
        res += getNum(nums, right);
        return res;
    }

    //    @Test
//    public void test() {
//        int[] ar = new int[]{1, 2, 2 ,1, 2, 1, 3};
//        System.out.println(getNum(ar, 3));
//        System.out.println(getNum(ar, 2));
//    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            StringBuilder sb = new StringBuilder();
            while (!in.hasNext("\\n")) {
                sb.append(in.next());
            }
            char[] ar = sb.toString().toCharArray();
            int[] nums = new int[ar.length];
            for (int j = 0; j < ar.length; j++) {
                nums[j] = Integer.valueOf(ar[j]);
            }
            for (int j = 0; j < ar.length; j++) {
                nums[j] = in.nextInt();
            }
            int left = getNum(nums, 2);
            int rigth = getNum(nums, 3);
            if (left == rigth) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

        }

    }

    @Test
    public void test() {
        String words = "aad nnn ddd   dddk";
        int n = words.length();
        char[] arr = words.toCharArray();
        int j = arr.length - 1, i = j;
        while (i >= 0) {
            while (i >= 0 && arr[i] != ' ') {
                i--;
            }
            System.out.println(words.substring(i + 1, j + 1));
            while (i >= 0 && arr[i] == ' ') {
                i--;
            }
            j = i;
        }
    }

    public int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 判断出栈顺序
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int m = popped.length;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && j < m && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test6() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{3, 1, 2, 4, 5};
        System.out.println(validateStackSequences(arr, arr2));
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head, tmp = head.next;
        while (tmp != null) {
            if (tmp.val == val) {
                pre.next = tmp.next;
                break;
            }
            pre = tmp;
            tmp = tmp.next;
        }
        return head;
    }

    @Test
    public void test8() {
        ListNode pre, head = new ListNode(1);
        pre = head;
        head.next = new ListNode(5);
        head = head.next;
        head.next = new ListNode(6);
        head = head.next;
        head.next = new ListNode(9);
        ListNode ans = deleteNode(pre, 5);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    /**
     * 树的层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int n = queue.size();
            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode tmp = queue.poll();
                assert tmp != null;
                a.add(tmp.val);
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
            ans.add(a);
        }
        return ans;
    }

    /**
     * 先求下三角，再求上三角
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        b[0] = 1;
        //求下三角
        for (int i = 1; i < n; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        int tmp = 1;
        for (int i = n - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }

        return b;
    }

    /**
     * 求最近的回文串，这个串是数字
     *
     * @param str
     * @return
     */
    public String nearestPalindromic(String str) {
        int n = str.length();

        int l = n / 2;
        String pre = str.substring(0, l);
        StringBuilder sb = new StringBuilder(pre);
        if (n % 2 == 0) {
            pre = pre + sb.reverse().toString();
        } else {
            pre = pre + str.charAt(l) + sb.reverse().toString();
        }
        return null;
    }

    /**
     * 使用二分法求根
     *
     * @param n
     * @return
     */
    public int getNum(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid * mid > n) {
                right = mid;
            } else if (mid * mid == n) {
                return mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
