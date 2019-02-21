package com.wp.weipu.test.threadandrunnable.executorExample;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * KNN k-近邻算法
 */
public class KNNClassifierParallelIndividual {
    private final List<? extends Sample> dataSet;
    private final int k;
    private final ThreadPoolExecutor executor;
    private final int numberThreads;
    private final boolean parallelSort;

    /**
     * @param dataSet      数据集
     * @param k            参数k
     * @param factor       处理器的线程数
     * @param parallelSort 是否要并行
     */
    public KNNClassifierParallelIndividual(List<? extends Sample> dataSet, int k, int factor, boolean parallelSort) {
        this.dataSet = dataSet;
        this.k = k;
        /**获取系统的线程数量**/
        this.numberThreads = factor * (Runtime.getRuntime().availableProcessors());
        //newFixedThreadPool接收线程数，返回Executor ，但是强转为ThreadPoolExecutor以便使用其方法
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(this.numberThreads);
        this.parallelSort = parallelSort;
    }

    public String classify(Sample sample) throws InterruptedException {
        Distances[] distances = new Distances[dataSet.size()];
        //java并发API提供的一种同步机制：CountDownLatch类。该类允许一个线程一直等待，知道其他线程代码到达其代码的某一确定点，
        //需要使用等待线程数进行初始化。两个方法：getDown():用于减少要等待的线程数；await()：该方法挂起调用它的线程，直到计数器达到0为止
        CountDownLatch enController = new CountDownLatch(dataSet.size());
        int index = 0;
        for (Sample localSample : dataSet) {
            IndividualDistanceTask task = new IndividualDistanceTask(distances, index, localSample, sample, enController);
            executor.execute(task);
        }
        enController.await();
        if (parallelSort) {
//            Arrays.parallelSort(distances);
        } else {
            Arrays.sort(distances);
        }
        return null;
    }


}
