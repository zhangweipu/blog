package com.wp.weipu.test.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 使用ArrayList实现栈的一些操作
 * 还可以使用链表实现，使用头插法
 */
public class MyStack {
    public List<Integer> arr;
    public int top;

    public MyStack() {
        this.arr = new ArrayList<>();
        this.top = -1;
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        top--;
        return this.arr.get(top);
    }

    public void push(int num) {
        this.arr.add(num);
        top++;
    }

    public int size() {
        return top + 1;
    }

    public Integer top() {
        if (isEmpty()) {
            return null;
        }
        return arr.get(top);
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "arr=" + arr +
                ", top=" + top +
                '}';
    }
}
