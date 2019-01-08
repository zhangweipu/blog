package com.wp.weipu.test.threadandrunnable.matrix.parallelmethod;

/**
 * 并发版本，每个结果元素一个线程，同时启动那么多线程会造成系统超载
 * 以一组启动十个线程
 */
public class IndividualMultiplierTask implements Runnable {

    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;
    private final int col;

    public IndividualMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int row, int col) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
        this.col = col;
    }

    /**
     * NOTUNDERSTAND
     * 一个run函数对应一个线程吗?
     */
    @Override
    public void run() {
        result[row][col] = 0;
        for (int k = 0; k < matrix1[row].length; k++) {
            result[row][col] += matrix1[row][k] * matrix2[k][col];
        }
    }
}
