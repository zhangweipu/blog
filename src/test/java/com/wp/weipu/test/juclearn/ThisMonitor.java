package com.wp.weipu.test.juclearn;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 试试，monitor
 */
public class ThisMonitor {

    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "enter to method");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "enter to method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
    }

    public static void main(String[] args) {
        ThisMonitor monitor = new ThisMonitor();
        new Thread(monitor::method1, "T1").start();
        new Thread(monitor::method2, "T2").start();
    }
}
