package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * todo:阻塞队列实现生产者，消费者
 */
public class ProducerConsumer {
    int capacity = 3;
    BlockingQueue queue = new ArrayBlockingQueue(capacity);

    class Producer extends Thread {
        @Override
        public void run() {
            try {
                //put 和add的区别是加了验证，还有等待有空间
                queue.put("produce");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("producer");
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consumer");
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            Producer p = new Producer();
            p.start();
        }
        for (int i = 0; i < 5; i++) {
            Consumer c = new Consumer();
            c.start();
        }
        for (int i = 0; i < 3; i++) {
            Producer p = new Producer();
            p.start();
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
