package com.wp.weipu.test.juclearn;

import java.util.concurrent.Semaphore;

/**
 * 使用信号量
 */
public class Semaphore2 {

    private Semaphore two = new Semaphore(0);
    private Semaphore three = new Semaphore(0);

    public Semaphore2() {
    }

    public void first(Runnable printFirst) {
        printFirst.run();
        //release会加一吗
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        two.acquire();
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThree) throws InterruptedException {
        three.acquire();
        printThree.run();
    }
}
