package com.wp.weipu.test.threadandrunnable.review;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

    private final static int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(availableProcessors,
            availableProcessors * 2, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void doSomethingA() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("打印一些信息A");
    }

    public static void doSomethingB() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("打印一些信息B");
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        threadPool.execute(() -> {
            try {
                doSomethingA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        doSomethingB();
        Future<?> resultA = threadPool.submit(() -> {
            try {
                doSomethingA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 区间染色，我不懂
     *
     * @param N 人数
     * @param b 区间开始
     * @param e 区间结束
     * @return 最短用时
     */
    public int numbers(int N, int[] b, int[] e) {
        boolean[] isForbidden = new boolean[N];
        //和颜色有关的
        int nMaxColors = 0, i, k, j;
        for (i = 0; i < N; i++) {
            for (k = 0; k < nMaxColors; k++) {
                isForbidden[k] = false;
            }
            for (j = 0; j < i; j++) {

            }
        }
        return 0;
    }

}
