package com.wp.weipu.test.threadandrunnable.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 随机生成二维数组，表示矩阵
 */
public class MatrixGenerator {

    public static final Logger logger = LoggerFactory.getLogger(MatrixGenerator.class);

    /**
     * 生成rows*cols的矩阵
     *
     * @param rows
     * @param cols
     * @return
     */
    public static double[][] generator(int rows, int cols) {
        logger.info("生成二维数组");
        double[][] result = new double[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = random.nextDouble() * 10;
            }
        }
        logger.info("生成数组");
        return result;
    }

    /**
     *  输出行列式
     * @param ret
     */
    public static void showMatrix(double[][] ret) {
        int rows = ret.length;
        int cols = ret[0].length;
        System.out.println("[");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("  " + ret[i][j]);
            }
            System.out.println();
        }
        System.out.println("]");
    }
}
