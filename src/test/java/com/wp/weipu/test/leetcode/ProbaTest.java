package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 某个策略出现最大值的概率
 */
public class ProbaTest {

    public int[] getRandomGold(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (int) Math.random();
        }
        return nums;
    }

    /**
     * 前四个房间不拿，接下来的四个房间中的一间只要比其四个的金子都多，则拿，不然拿最后一个
     *
     * @return
     */
    public int getGold(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            max = Math.min(max, nums[i]);
        }
        for (int i = 4; i < 8; i++) {
            if (nums[i] > max) {
                max = nums[i];
                return max;
            }
        }
        return nums[len - 1];
    }

    public int getMaxGold(int[] nums) {
        int max = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    /**
     * @param nums
     * @param n
     * @return
     */
    public void getAllCombination(int[] nums, int n, int count) {
        //展示数据
        if (n == 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(nums[i]);
            }
            System.out.println("");
            return;
        }

        int i = (count == 0) ? 1 : nums[count - 1];
        for (; i <= n; i++) {
            nums[count] = i;
            getAllCombination(nums, n - i, count + 1);
        }
    }

    @Test
    public void test() {
        int n = 4;
        int[] nums = new int[n];
        getAllCombination(nums, n, 0);
    }

    /**
     * 不排序数组中寻找，第k大的数
     * 尝试一下半排序的二分查找
     *
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int index = len - k;
        Arrays.sort(nums);

        return nums[index];
    }

    @Test
    public void test1() {
        int[] num = {1, 4, 2, 4, 7, 3, 5};
        findKthLargest(num, 4);
    }

    /**
     * 寻找最长公共部分
     *
     * @return
     */
    public int findLength(int[] a, int[] b) {
        int lenA = a.length, lenB = b.length;
        int[][] matrix = new int[lenA + 1][lenB + 1];
        int max = 0;
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (a[i - 1] == b[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    max = Math.max(max, matrix[i][j]);
                }
            }
        }
        return max;
    }

    /**
     * 使用滑动的方式
     *
     * @param a
     * @param b
     * @return
     */
    public int findLength1(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int ret = 0;
        // 保持b不动
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxLen = maxLength(a, b, i, 0, len);
            ret = Math.max(maxLen, ret);
        }
        //保持a不动
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxLen = maxLength(a, b, 0, i, len);
            ret = Math.max(ret, maxLen);
        }
        return ret;
    }

    /**
     * 这就是相同位置比较
     *
     * @param A
     * @param B
     * @param addA
     * @param addB
     * @param len
     * @return
     */
    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }

    /**
     * 合并两个有序列表
     */
    public ListNode mergeTwoList(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        //这样就不能区分大小了
        ListNode head;
        if (a.val > b.val) {
            head = b;
            b = b.next;
        } else {
            head = a;
            a = a.next;
        }
        head.next = null;
        while (a != null && b != null) {
            if (a.val > b.val) {
                head.next = b;
                b = b.next;
            } else {
                head.next = a;
                a = a.next;
            }
            head = head.next;
            head.next = null;
        }
        if (a != null) {
            head.next = a;
        }
        if (b != null) {
            head.next = b;
        }
        return head;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        //排序队列
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2 + 1]});
            }
        }
        return pq.poll()[0];
    }

    @Test
    public void test3() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        pq.offer(new int[]{1, 3, 4});
        System.out.println(pq.iterator().next());
    }

}
