package com.wp.weipu.test.threadandrunnable.matrix.pallelByAvailableProcessor;

import com.wp.weipu.test.threadandrunnable.matrix.MatrixGenerator;
import org.junit.Test;

/**
 * 数据太多，超慢
 */
public class ParalleRowlMain {

    @Test
    public void test1() {
        double[][] matrix1 = MatrixGenerator.generator(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generator(2000, 2000);
        double[][] result = new double[matrix1.length][matrix2[0].length];
        Long start = System.currentTimeMillis();
        PallelRowMultiplier.multiplier(matrix1, matrix2, result);
        Long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) + "毫秒");

    }
}
