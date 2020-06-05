package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LearnForLIst {
    /**
     * 原地反转
     *
     * @param head
     */
    public void reverse(LNode head) {
        LNode cur, next;
        cur = head.next;
        next = cur.next;
        cur.next = null;
        while (next != null) {
            cur = next;
            next = next.next;
            cur.next = head.next;
            head.next = cur;
        }
    }

    /**
     * 使用递归反转
     *
     * @param head
     */
    public LNode recursiveReverse(LNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LNode newHead = recursiveReverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 递归显示倒叙
     *
     * @param head
     */
    public void recursivePrint(LNode head) {
        if (head == null) {
            return;
        }
        recursivePrint(head.next);
        System.out.println(head.val);
    }

    /**
     * 移除无序列表的重复项
     * 空间换时间的做法
     *
     * @param head
     */
    public void removeDup(LNode head) {
        //直接存值就行了
        Map<Integer, LNode> have = new HashMap<>();
        //这里没有关注头节点
        LNode pre, cur;
        pre = head;
        cur = pre.next;
        while (cur != null) {
            if (have.get(cur.val) != null && cur.val == have.get(cur.val).val) {
                pre.next = cur.next;
            } else {
                have.put(cur.val, cur);
            }
            pre = pre.next;
            if (pre == null) {
                break;
            }
            cur = pre.next;
        }
        System.out.println("aaaa");
    }

    /**
     * 递归的方式，删除重复
     *
     * @param head
     */
    public LNode removeDupRecursion(LNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeDupRecursion(head.next);
        LNode pre, next;
        pre = head;
        next = pre.next;
        while (next != null) {
            if (head.val == next.val) {
                //在这里加一个就可以避免使用break
                pre.next = next.next;
            }
            pre = pre.next;
            if (pre == null) {
                break;
            }
            next = pre.next;
        }
        return head;
    }

    /**
     * 两个链表做加法
     * 都是带头节点的
     *
     * @param a
     * @param b
     * @return
     */
    public LNode add(LNode a, LNode b) {
        LNode p1 = a.next, p2 = b.next;
        // 使用链表存结果
        LNode res = new LNode(-1);
        int sum = 0;
        int carry = 0;
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + carry;
            carry = 0;
            if (sum >= 10) {
                //进位
                carry = 1;
                sum = sum % 10;
            }
            res.next = new LNode(sum);
            res = res.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        while (p1 != null) {
            sum = p1.val + carry;
            carry = 0;
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
            }
            res.next = new LNode(sum);
            p1 = p1.next;
        }
        while (p2 != null) {
            sum = p2.val + carry;
            carry = 0;
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
            }
            res.next = new LNode(sum);
            p2 = p2.next;
        }
        return res;
    }

    /**
     * 链表重排
     * 先找到中间的节点，反转后半段，最后执行合并
     *
     * @param head
     */
    public void reOrder(LNode head) {
        LNode fast, slow, pre;
        fast = head;
        //我需要断开中间的部分
        slow = head;
        pre = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            pre = slow.next;
            slow = slow.next.next;
        }
        pre.next = null;
        LNode tmp = head;
        LNode cur = head.next, cur2 = slow.next;
        recursiveReverse(slow);
        while (cur.next != null) {
            tmp.next = slow;
            slow.next = cur;
            cur = cur.next;
            slow = cur2;
            cur2 = cur2.next;
            tmp = tmp.next;
        }


    }

    public void rotateK(LNode head, int k) {
        if (head == null || head.next == null) {
            return;
        }
        LNode fast, slow, pre = head;
        fast = slow = head.next;
        int i;
        for (i = 0; i < k && slow != null; i++) {
            slow = slow.next;
        }
        if (i < k) {
            return;
        }
        while (slow.next != null) {
            pre = fast;
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = head.next;
        head.next = fast;
        pre.next = null;
    }

    /**
     * 默认有头节点吧
     * 快慢指针
     *
     * @param head
     */
    public LNode getLastK(LNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LNode slow, fast;
        slow = fast = head.next;
        int i;
        for (i = 0; i < 5 && fast != null; i++) {
            fast = fast.next;
        }
        if (i < 5) {
            return null;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 判断是否有环
     * 怎么可以确定没有环
     *
     * @param head
     * @return
     */
    public LNode isLoop(LNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        LNode fast, slow;
        fast = head.next;
        slow = head.next;
        // 如果无限循环则说明有环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                // 返回相遇点
                return slow;
            }
        }
        return null;
    }

    /**
     * 查找环的入口
     * 需要借助他们相遇的节点
     *
     * @param head
     * @param meetNode
     * @return
     */
    public LNode findLoopNode(LNode head, LNode meetNode) {
        LNode first = head.next;
        LNode second = meetNode;
        while (first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }

    public LNode createList() {
        LNode head = new LNode(-1);
        LNode tmp = head;
        for (int i = 0; i < 10; i++) {
            tmp.next = new LNode(i);
            tmp = tmp.next;
        }
        tmp.next = new LNode(1);
        tmp.next = new LNode(2);
        return head;
    }

    /**
     * 相邻的节点反转
     *
     * @param head
     */
    public void reverse2(LNode head) {
        if (head == null || head.next == null) {
            return;
        }
        LNode cur = head.next;
        LNode pre = head;
        // 判断null需要判断到第几个
        while (cur != null && cur.next != null) {
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = cur;
            pre = cur;
            cur = cur.next;
        }
    }

    /**
     * 不带头节点的链表反转
     *
     * @param head
     */
    public LNode reverseNoHead(LNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LNode pre = head;
        LNode cur = head.next;
        pre.next = null;
        LNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 有头节点的，每k个反转一次
     * 主要是反转那一段
     *
     * @param head
     */
    public void reverseK(LNode head, int k) {
        if (head == null || head.next == null) {
            return;
        }
        LNode pre = head;
        LNode begin = head.next;
        LNode end = head.next;
        while (begin != null) {
            int i;
            for (i = 1; i < k && end.next != null; i++) {
                end = end.next;
            }
            if (i < k) {
                pre.next = begin;
                break;
            }
            LNode next = end.next;
            end.next = null;
            //在这里begin已经反过来了
            pre.next = reverseNoHead(begin);
            //定位到下一段的前面
            while (pre.next != null) {
                pre = pre.next;
            }
            begin = next;
            end = next;
        }
    }

    /*
    合并两个有序链表,带头节点的两个有序链表
     */
    public LNode merge(LNode a, LNode b) {
        if (a == null || a.next == null) {
            return b;
        }
        if (b == null || b.next == null) {
            return a;
        }
        LNode cur1 = a.next;
        LNode cur2 = b.next;
        LNode newHead, pre;
        // 判断大小，获取头节点
        if (cur1.val < cur2.val) {
            newHead = a;
            pre = cur1;
            cur1 = cur1.next;
        } else {
            newHead = b;
            pre = cur2;
            cur2 = cur2.next;
        }
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
                pre = pre.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
                pre = pre.next;
            }
        }
        // 他们俩其中一个会为空
        if (cur1 != null) {
            pre.next = cur1;
        }
        if (cur2 != null) {
            pre.next = cur2;
        }
        return newHead;
    }

    public void removeNode(LNode remove) {
        if (remove == null || remove.next == null) {
            return;
        }
        LNode tmp = remove.next;
        remove.val = tmp.val;
        // 节点没有引用的话是，jvm自动释放内存吧
        tmp = tmp.next;
        remove.next = tmp;
    }

    /**
     * 判断两个链表是否有交叉
     * 使用首尾相接法，判断有没有环
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isIntersect(LNode a, LNode b) {
        if (a == null || a.next == null) {
            return false;
        }
        if (b == null && b.next == null) {
            return false;
        }
        LNode head = a;
        LNode cur = a.next;
        while (cur.next != null) {
            cur = cur.next;
        }
        // 连接b的开头
        cur.next = b.next;
        //如果两个链表有交叉，组成一个头节点为head的环
        LNode fast, slow;
        fast = head.next;
        slow = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        LNode head = createList();
        reverseK(head, 3);
//        recursivePrint(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }
}
