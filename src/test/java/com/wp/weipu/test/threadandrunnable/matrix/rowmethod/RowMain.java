package com.wp.weipu.test.threadandrunnable.matrix.rowmethod;

import com.wp.weipu.test.threadandrunnable.matrix.MatrixGenerator;
import com.wp.weipu.test.threadandrunnable.matrix.parallelmethod.ParallelIndividualMultiplier;
import org.junit.Test;

public class RowMain {
    @Test
    public void test1() {
        double[][] matrix1 = MatrixGenerator.generator(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generator(2000, 2000);
        double[][] result = new double[matrix1.length][matrix2[0].length];
        Long start = System.currentTimeMillis();
        ParallelIndividualMultiplier.multiply(matrix1, matrix2, result);
        Long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) + "毫秒");

    }
}
