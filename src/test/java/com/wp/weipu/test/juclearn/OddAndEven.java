package com.wp.weipu.test.juclearn;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class OddAndEven {

    private int num;

    public OddAndEven(int num) {
        this.num = num;
    }

    public void odd(Runnable oddPrint) throws InterruptedException {
        while (num > 0) {
            synchronized (this) {
                if (num % 2 == 0) {
                    oddPrint.run();
                    num--;
                    this.notify();
                }else {
                    this.wait();
                }
            }
        }
    }

    public void even(Runnable evenPrint) throws InterruptedException {

        while (num > 0) {
            synchronized (this) {

                if (num % 2 != 0) {
                    evenPrint.run();
                    num--;
                    this.notify();
                }else {
                    this.wait();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OddAndEven oe = new OddAndEven(10);
        Thread eve = new Thread("thread1") {
            public void run() {
                try {
                    //多线程的执行方法。。。
                    oe.even(new Even());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread odd = new Thread("thread2") {
            public void run() {
                try {
                    oe.odd(new Odd());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        eve.start();
        odd.start();
        eve.join();
        odd.join();
    }

    static class Even implements Runnable {

        @Override
        public void run() {
            System.out.println("even");
        }
    }

    static class Odd implements Runnable {

        @Override
        public void run() {
            System.out.println("odd");
        }
    }
}
