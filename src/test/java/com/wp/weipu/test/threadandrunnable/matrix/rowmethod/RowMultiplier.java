package com.wp.weipu.test.threadandrunnable.matrix.rowmethod;

import java.util.ArrayList;
import java.util.List;

public class RowMultiplier {

    public static void multiplier(double[][] matrix1, double[][] matrix2, double[][] reuslt) {
        List<Thread> threads = new ArrayList<>();
        int row = matrix1.length;
        for (int i = 0; i < row; i++) {
            RowMultipierTask task = new RowMultipierTask(matrix1, matrix2, reuslt, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            if (threads.size() % 10 == 0) {
                waitForThreads(threads);
            }
        }

    }

    private static void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
