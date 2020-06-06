package com.wp.weipu.test.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyQueue {

    Queue queue = new ArrayDeque();
    public DLNode QHead;
    public DLNode QEnd;

    public MyQueue() {
        this.QEnd = new DLNode(-1);
        this.QHead = new DLNode(-1);
        this.QHead.rear = this.QEnd;
        this.QEnd.prior = this.QHead;
    }

    public void push(int val) {

    }

    class DLNode {
        DLNode prior;
        DLNode rear;
        int val;

        public DLNode(int val) {
            this.val = val;
        }
    }
}
