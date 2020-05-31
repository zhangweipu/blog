package com.wp.weipu.test.leetcode;

import org.junit.Test;
import org.yaml.snakeyaml.util.ArrayStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SolutionTwo {

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node1, node2, node3;
        node1 = head;
        node2 = head.next;
        //没倒数第二个啊
        if (node2.next == null) {
            return node2;
        }
        //多走n+1步
        node3 = node2;
        for (int i = 0; i < n; i++) {
            node2 = node2.next;
        }
        while (node2.next != null) {
            node1 = node1.next;
            node2 = node2.next;
            node3 = node3.next;
        }
        node1.next = node3.next;
        return head;
    }

    @Test
    public void treatd() {
        List sub = new ArrayList();
        sub.add('s');
        sub.add('d');
        System.out.println(sub.get(0));
        if (sub.get(0).equals('s')) {
            System.out.println("no");
        }
        sub.remove(0);
    }

    public boolean isValid(String s) {
        if (s.charAt(0) == '}' || s.charAt(0) == ']' || s.charAt(0) == ')') {
            return false;
        }
        char[] ss = s.toCharArray();
        int len = 0;
        char[] stack = new char[ss.length];
        int top = 0;
        while (len < ss.length) {
            if (ss[len] == '(' || ss[len] == '[' || ss[len] == '{') {
                stack[top++] = ss[len];
//                top++;
//                len++;
            } else if (top > 0 && ((stack[top - 1] == '(' && ss[len] == ')') || (stack[top - 1] == '{' && ss[len] == '}') ||
                    (stack[top - 1] == '[' && ss[len] == ']'))) {
                top--;
            } else if (ss[len] == ']' || ss[len] == '}' || ss[len] == ')') {
                return false;
            }
            len++;
        }
        if (top == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void t() {
        String s = "()]";
        if (isValid(s)) {
            System.out.println("true");
        }
    }

}
