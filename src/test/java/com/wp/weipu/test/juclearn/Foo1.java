package com.wp.weipu.test.juclearn;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo1 {

    //原子类
    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJodDone = new AtomicInteger(0);

    public void first(Runnable printFirst) {
        printFirst.run();
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) {
        while (firstJobDone.get() != 1) {

        }
        printSecond.run();
        secondJodDone.incrementAndGet();
    }

    public void third(Runnable printThird) {
        while (secondJodDone.get() != 1) {

        }
        printThird.run();
    }
}
