package com.wp.weipu.test.review.leetcodebook.array;

import com.wp.weipu.test.leetcode.LNode;

import java.util.HashMap;
import java.util.Map;

public class Array1 {
    /**
     * o(n)的时间复杂度
     * 使用map记录访问过的值
     *
     * @param array
     * @param target
     * @return
     */
    public int[] twoSum(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int sub = target - array[i];
            if (map.containsKey(sub)) {
                return new int[]{array[i], sub};
            }
            map.put(array[i], i);
        }
        return null;
    }

    /**
     * 寻找中位数，时间复杂度在o(log(n+m))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        //交换数组位置，还不用判断这样挺好的
        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0, high = nums1.length, k = (n + m) >> 1;
        int midNums1 = 0, midNums2 = 0;
        while (low < high) {
            midNums1 = low + (low + high) >> 1;
            midNums2 = k - midNums1;
            //判断中位数的关键在这里
            //nums1的分界线画多了，向左移动
            if (midNums1 > 0 && nums1[midNums1 - 1] > nums2[midNums2]) {
                high = midNums1 - 1;
                //nums1的分界线画少了，向右移
            } else if (midNums1 != n && nums1[midNums1] < nums2[midNums2 - 1]) {
                low = midNums1 + 1;
            } else {
                //找到合适的划分了，跳出
                break;
            }
        }
        //长度是奇数，偶数两种情况
        int midLeft = 0, midRight = 0;
        if (midNums1 == 0) {
            midLeft = nums2[midNums2 - 1];
        } else if (midNums2 == 0) {
            midLeft = nums1[midNums1 - 1];
        } else {
            midLeft = Math.max(nums1[midNums1 - 1], nums2[midNums2 - 1]);
        }
        //奇数情况
        if (((n + m) & 1) == 1) {
            return midLeft;
        }
        if (midNums1 == n) {
            midRight = nums2[midNums2];
        } else if (midNums2 == m) {
            midRight = nums1[midNums1];
        } else {
            midRight = Math.min(nums1[midNums1], nums2[midNums2]);
        }
        return (midLeft + midRight) >> 1;
    }

    /**
     * 原地反转链表
     *
     * @param head
     * @return
     */
    public LNode reverseList(LNode head) {
        LNode cur = head, pre = null;
        while (cur != null) {
            LNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
