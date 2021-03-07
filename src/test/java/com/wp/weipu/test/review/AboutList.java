package com.wp.weipu.test.review;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 关于链表的相关练习
 */
public class AboutList {

    protected class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 通过递归反转链表
     *
     * @param head
     * @return
     */
    public ListNode recuReverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        //这样确保返回的是最后一个节点
        ListNode last = recuReverse(head.next);
        //采用的尾插法
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 原地反转链表
     *
     * @param head
     * @return
     */
    public ListNode localReverser(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode newNode = null;
        while (head.next != null) {
            //保存下一个节点，借用了一个额外的地址
            ListNode tmp = head.next;
            head.next = newNode;
            newNode = head;
            head = tmp;
        }
        return newNode;
    }

    /**
     * 反转链表后n个节点
     * 用栈需要大量的
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head, int n) {
        ListNode tmp = new ListNode(-1);
        LinkedList<ListNode> stack = new LinkedList<>();
        while (head.next != null) {
            ListNode next = head.next;
            head.next = null;
            stack.push(head);
            head = next;
        }
        //链表长度不够n还要做判断的啊
        ListNode l = tmp, newHead = null;
        for (int i = 0; i < n; i++) {
            tmp.next = stack.pollLast();
            tmp = tmp.next;
        }
        while (!stack.isEmpty()) {
            newHead = stack.pollLast();
            newHead.next = l;
            l = newHead;
        }
        return newHead;
    }

    /**
     * 反转前n个
     * 当需要返回多个结果的时候，就要考虑借用全局的变量了
     *
     * @param head
     * @param n
     * @return
     */
    //记录反转之后的一段
    ListNode successor = null;

    public ListNode reversePreN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reversePreN(head.next, n - 1);
        //这一段可能不好理解。。。。
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * 区间取反链表
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode newHead = null;
        while (a != b) {
            ListNode tmp = a.next;
            a.next = newHead;
            newHead = a;
            a = tmp;
        }
        return newHead;
    }

    /**
     * 以k个节点为一组，进行反转
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseGroup(b, k);
        return newHead;
    }

    /**
     * 分隔链表
     * 直接用两个链表记录
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1), pre = small;
        ListNode big = new ListNode(-1), preb = big;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        small.next = preb.next;
        return pre.next;
    }

    @Test
    public void test17() {
        ListNode ll = initList();
        ll = partition(ll, 3);
        while (ll != null) {
            System.out.println(ll.val);
            ll = ll.next;
        }
    }

    /**
     * 用于测试的，初始化一个链表
     *
     * @return
     */
    public ListNode initList() {
        ListNode head = new ListNode(1), cur = head;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
        return head;
    }
}
