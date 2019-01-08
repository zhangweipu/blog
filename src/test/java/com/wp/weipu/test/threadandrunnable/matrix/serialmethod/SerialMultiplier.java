package com.wp.weipu.test.threadandrunnable.matrix.serialmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 串行本版实现矩阵相乘
 */
public class SerialMultiplier {
    public static final Logger logger = LoggerFactory.getLogger(SerialMultiplier.class);

    /**
     * 计算矩阵乘法
     *
     * @param matrix1
     * @param matrix2
     */
    public static double[][] multiplier(double[][] matrix1, double[][] matrix2) {

        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int col2 = matrix2[0].length;
        double[][] result = new double[row1][col2];
        for (int row = 0; row < row1; row++) {
            for (int col = 0; col < col2; col++) {
                result[row][col] = 0;
                for (int m = 0; m < col1; m++) {
                    result[row][col] += matrix1[row][m] + matrix2[m][col];
                }
            }
        }
        return result;
    }
}
