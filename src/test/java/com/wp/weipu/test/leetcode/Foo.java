package com.wp.weipu.test.leetcode;

import java.util.concurrent.Semaphore;

public class Foo {

    private Semaphore semaSecond = new Semaphore(0);
    private Semaphore semaThird = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        semaSecond.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaSecond.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        semaThird.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaThird.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
