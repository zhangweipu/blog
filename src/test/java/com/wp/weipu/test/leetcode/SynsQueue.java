package com.wp.weipu.test.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SynsQueue {

    private int[] data;
    private int len;
    private int capacity;

    public SynsQueue(int capacity) {
        this.data = new int[capacity];
        this.len = 0;
        this.capacity = capacity;
    }

    public void put(int n) {
        synchronized (this) {
            if (getLen() >= this.capacity) {
                throw new IllegalStateException("队列满了");
            }
            data[len] = n;
        }
    }

    public int getLen() {
        return this.len;
    }
}
