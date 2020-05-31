package com.wp.weipu.test.leetcode;

import org.junit.Test;

public class ListOfQuestion {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        cur.next = lists[0];
        do {
            int index = 0;
            for (int i = 0; i < len; i++) {
                if (lists[i] != null && cur.val > lists[i].val) {
                    cur = lists[i];
                    index = i;
                }
            }
            if (lists[index] != null) {
                lists[index] = lists[index].next;
                cur.next = lists[index];
            }
            cur = cur.next;
        } while (cur != null);
        return head.next;
    }

    @Test
    public void test() {
//        ListNode[] list = new ListNode[4];
        ListNode list1 = new ListNode(1);
        ListNode l1 = list1;
        list1.next = new ListNode(2);
        list1 = list1.next;
        list1.next = new ListNode(3);
        list1 = list1.next;
        ListNode list2 = new ListNode(4);
        list2.next = null;

        list1.next = list2;
//        ListNode l2 = list2;
//        list2.next = new ListNode(3);
//        list2 = list2.next;
//        list2.next = new ListNode(4);
//        ListNode list3 = new ListNode(2);
//        ListNode l3 = list3;
//        list3.next = new ListNode(6);
//        list[0] = l1;
//        list[1] = l2;
//        list[2] = l3;
//        ListNode res = mergeKLists(list);
//        while (res != null) {
//            System.out.println(res.val);
//            res = res.next;
//        }
//        while (l1 != null) {
//            System.out.println(l1.val);
//            l1 = l1.next;
//        }
        ListNode m = swapPairs(l1);
        while (m != null) {
            System.out.println(m.val);
            m = m.next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //合并两个链表
        if (l2 == null) {
            return l1;
        } else if (l1 == null) {
            return l2;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        do {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        } while (l1 != null || l2 != null);
        return head.next;
    }


    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0), cur;
        cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                while (l1.next != null && l1.next.val < l2.val) {
                    l1 = l1.next;
                }
                cur = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                while (l2.next != null && l2.next.val < l1.val) {
                    l2 = l2.next;
                }
                cur = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return head.next;
    }

    public ListNode swapPairs(ListNode head) {
        //为了放置开头
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prevNode = dummy;
        while (head != null && head.next != null) {
            ListNode firstNode = head;
            ListNode secondNode = head.next;
            //交换
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            prevNode = firstNode;
            head = firstNode.next;
        }

        return dummy.next;
    }
}

