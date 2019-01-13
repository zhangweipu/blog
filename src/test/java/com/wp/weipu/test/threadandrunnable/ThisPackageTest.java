package com.wp.weipu.test.threadandrunnable;

import com.wp.weipu.test.threadandrunnable.matrix.MatrixGenerator;
import org.junit.Test;

public class ThisPackageTest {

    @Test
    public void test1() {

        double[][] data = MatrixGenerator.generator(6, 5);
        int rows = data.length;
        int cols = data[0].length;
        System.out.println("[");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(" " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("]");
    }
}
