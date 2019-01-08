package com.wp.weipu.test.threadandrunnable.matrix.parallelmethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建所有必要的执行线程计算结果矩阵
 */
public class ParallelIndividualMultiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        //线程列表
        List<Thread> threads = new ArrayList<>();
        int row1 = matrix1.length;
        int row2 = matrix2.length;
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < row2; j++) {
                IndividualMultiplierTask task = new IndividualMultiplierTask(matrix1, matrix2, result, i, j);
                Thread thread = new Thread(task);
                thread.start();
                threads.add(thread);
                if (threads.size() % 10 == 0) {
                    waitForThreads(threads);
                }
            }
        }
    }

    /**
     * 等待这十个线程结束
     *
     * @param threads
     */
    private static void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
