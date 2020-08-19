package com.wp.weipu.test.juclearn.executorlearn;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 试试lambda运行线程
 */
public class Test1 {

    @Test
    public void test() {
        IntStream.range(0, 5).boxed().map(i -> new Thread(()
                -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);
    }

    /**
     * 为什么会不一样呢
     *
     * @param args
     */
    public static void main(String[] args) {
        IntStream.range(0, 5).boxed().map(i -> new Thread(()
                -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);

        IntStream.range(0, 5).mapToObj(Test1::createThread).forEach(Thread::start);
    }

    private static final String name = "AAA-";

    private static Thread createThread(final int intName) {
        return new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, name + intName);
    }
}
