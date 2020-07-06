package com.wp.weipu.test.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印数字
 * 不会用法这种反方式
 */
public class PrintOddAndEven2 {

    public int value = 0;
    private Lock lock1 = new ReentrantLock();
//    private Lock lock2 = new ReentrantLock();
    Condition condition = lock1.newCondition();
//    Condition condition1 = lock2.newCondition();

    public void printOdd() throws InterruptedException {
        while (value < 100) {
            if (value % 2 == 1) {

                lock1.lock();
                try {
                    System.out.println(Thread.currentThread() + "----" + value);
                    value++;
                    condition.signal();
                } finally {
                    lock1.unlock();
                }
            } else {
                condition.await();
            }
        }
    }

    public void printEven() throws InterruptedException {
        while (value < 100) {
            if (value % 2 == 0) {

                lock1.lock();
                try {
                    System.out.println(Thread.currentThread() + "-----" + value);
                    value++;
                    condition.signal();
                } finally {
                    lock1.unlock();
                }

            } else {
                condition.await();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        PrintOddAndEven2 p = new PrintOddAndEven2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        PrintOddAndEven2 example = new PrintOddAndEven2();
        executorService.execute(() -> {
            try {
                example.printEven();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                example.printOdd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
