package com.wp.weipu.test.juclearn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestTask {
    public static void main(String[] args) {
        int threadNumber = 1;
        System.out.println("单线程执行结果：");
        for (int i = 0; i < 5; i++) {
            System.out.println(testAdd(threadNumber));
        }
        threadNumber=5;
        System.out.println("多线程执行结果：");
        for (int i=0;i<5;i++){
            System.out.println(testAdd(threadNumber));
        }
    }

    private static int testAdd(int threadNumber) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 0);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNumber; i++) {
            pool.execute(new Test(map));
        }
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map.get(1);
    }
}

class Test implements Runnable {
    private ConcurrentHashMap<Integer, Integer> map;

    public Test(ConcurrentHashMap<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            map.put(1, map.get(1) + 1);
        }
    }
}
