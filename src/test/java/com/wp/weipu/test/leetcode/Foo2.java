package com.wp.weipu.test.leetcode;

import java.util.concurrent.CountDownLatch;

public class Foo2 {

    private CountDownLatch second = new CountDownLatch(1);
    private CountDownLatch third = new CountDownLatch(1);

    public Foo2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        second.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        third.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        second.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
