package com.wp.weipu.test.leetcode;

/**
 * 使用同步的方式实现打印奇偶数
 */
public class PrintOddAndEven {
    int value = 0;
    char s = 'a' - 1;
    int b = 0;

    public synchronized void printOdd() {
        while (value < 100) {
            if (value % 2 == 1) {
                System.out.println(Thread.currentThread() + "-" + s++);
                value++;
                this.notify();
            } else {
                try {
                    //不是偶数就等待
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void printEven() {
        while (value < 100) {
            if (value % 2 == 0) {
                System.out.println(Thread.currentThread() + "-" + b++);
                value++;
                //随意唤醒一个挂起的线程
                this.notify();
            } else {
                try {
                    //不满足条件挂起
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintOddAndEven p = new PrintOddAndEven();
        Thread t1 = new Thread(p::printOdd);
        Thread t2 = new Thread(p::printEven);
        t2.start();
        t1.start();
        t1.join();
        t2.join();
    }
}
