package com.wp.weipu.test.threadandrunnable.executorExample;

/**
 * 求欧式矩阵
 */
public class EuclideanDistanceCalulator {

    public static double calculate(Sample example1, Sample example2) {
        double ret = 0.0d;
        double[] data1 = example1.getExample();
        double[] data2 = example2.getExample();
        if (data1.length != data2.length) {
            System.out.println("两个向量长度不一样");
        }
        for (int i = 0; i < data1.length; i++) {
            ret += Math.pow(data1[i] - data2[i], 2);
        }
        return Math.sqrt(ret);
    }
}
