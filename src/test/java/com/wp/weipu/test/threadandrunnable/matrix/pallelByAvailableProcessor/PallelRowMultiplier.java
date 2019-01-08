package com.wp.weipu.test.threadandrunnable.matrix.pallelByAvailableProcessor;


import java.util.ArrayList;
import java.util.List;

public class PallelRowMultiplier {

    public static void multiplier(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();
        int row1 = matrix1.length;
        int numberThreads = Runtime.getRuntime().availableProcessors();
        int startIndex, endIndex, step;
        step = row1 / numberThreads;
        startIndex = 0;
        endIndex = step;
        for (int i = 0; i < numberThreads; i++) {
            PallelRowMultiplierTask task = new PallelRowMultiplierTask(matrix1, matrix2, result, startIndex, endIndex);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            startIndex = endIndex;
            endIndex = i == numberThreads - 2 ? row1 : endIndex + step;
        }
        for (Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
