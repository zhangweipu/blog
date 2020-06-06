package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class StackLearn {
    /**
     * 反转栈O(n)
     * 分两步，第一步把栈底拿到栈顶，然后再递归没有栈顶的栈
     * 使用了两次递归
     *
     * @param stack
     */
    public void reverseStack(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        }
        bottomToTop(stack);
        int top = stack.pop();
        reverseStack(stack);
        stack.push(top);
    }

    /**
     * 这一步只是为了让栈底到栈顶，其他的不变
     *
     * @param stack
     */
    public void bottomToTop(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        }
        int top1 = stack.pop();
        if (!stack.empty()) {
            bottomToTop(stack);
            int top2 = stack.pop();
            stack.push(top1);
            stack.push(top2);
        } else {
            stack.push(top1);
        }
    }

    /**
     * 判读一个序列是否为另一个序列的出栈序列
     *
     * @param push
     * @param pop
     * @return
     */
    public boolean isPopSerial(String push, String pop) {
        if (push == null || pop == null) {
            return false;
        }
        if (push.length() != pop.length()) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        int index = 0;
        for (char c : push.toCharArray()) {
            stack.push(c);
            while (!stack.empty() && stack.peek() == pop.charAt(index)) {
                stack.pop();
                index++;
            }
        }
        if (index == pop.length()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 使用两个栈，模拟队列
     */
    class StackQueue<T> {
        public Stack<T> push = new Stack<>();
        public Stack<T> pop = new Stack<>();

        public StackQueue() {

        }

        /**
         * 进队
         *
         * @param val
         * @return
         */
        public void enQueue(T val) {
            push.push(val);
            if (pop.empty()) {
                while (!push.empty()) {
                    pop.push(push.pop());
                }
            }
        }

        /**
         * 出队
         *
         * @return
         */
        public T deQueue() {
            if (pop.empty() && !push.empty()) {
                while (!push.empty()) {
                    pop.push(push.pop());
                }
            }
            if (pop.empty()) {
                return null;
            } else {
                return pop.pop();
            }
        }

    }

    /**
     * 根据一堆车票，打印出路径
     *
     * @param maps
     */
    public void printResult(HashMap<String, String> maps) {
        //存放终点的
        Set<String> toAd = new HashSet<>();
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            toAd.add(entry.getValue());
        }
        String start = null;
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            if (!toAd.contains(entry.getKey())) {
                start = entry.getKey();
            }
        }
        assert start == null : "没找到";
        while (start != null) {
            System.out.println(maps.get(start));
            start = maps.get(start);
        }
    }

    @Test
    public void test() {
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < 10; i++) {
//            stack.push(i);
//        }
//        reverseStack(stack);
//        while (!stack.empty()) {
//            int top = stack.pop();
//            System.out.println(top);
//        }

        String push = "12345";
        String pop = "53241";
        if (isPopSerial(push, pop)) {
            System.out.println("是");
        } else {
            System.out.println("不是");
        }
    }
}
