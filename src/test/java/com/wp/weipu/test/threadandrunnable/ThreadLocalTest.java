package com.wp.weipu.test.threadandrunnable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    ThreadLocal<String> args = new ThreadLocal<>();

    @Test
    public void tes() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String arg = "aa";
                args.set(arg);
                task1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String arg = "bb";
                args.set(arg);
                task1();
            }
        });
        t1.start();
        t2.start();
        //不加这个，可能主线程执行完了，而这两个线程还没开始跑
        t1.join();
        t2.join();
    }

    public void task1() {
        task2();
    }

    public void task2() {
        System.out.println(args.get());
    }

    @Test
    public void test2() {
        ArrayList<Integer> list = new ArrayList<>();
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                list.add(1);
            });
            executorService.execute(() -> {
                for (Integer v : list) {
                    System.out.println(v);
                }
            });
        }
    }
}
