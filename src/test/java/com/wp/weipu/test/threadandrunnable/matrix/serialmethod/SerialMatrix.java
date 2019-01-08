package com.wp.weipu.test.threadandrunnable.matrix.serialmethod;

import com.wp.weipu.test.threadandrunnable.matrix.MatrixGenerator;
import org.junit.Test;


public class SerialMatrix {

    @Test
    public void test1() {
        double[][] matrix1 = MatrixGenerator.generator(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generator(2000, 2000);
        Long start = System.currentTimeMillis();
        double[][] result = SerialMultiplier.multiplier(matrix1, matrix2);
        Long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) + "毫秒");
//        MatrixGenerator.showMatrix(result);
    }
}
