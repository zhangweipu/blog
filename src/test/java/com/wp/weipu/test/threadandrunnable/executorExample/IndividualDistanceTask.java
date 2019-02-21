package com.wp.weipu.test.threadandrunnable.executorExample;

import java.util.concurrent.CountDownLatch;

public class IndividualDistanceTask implements Runnable {
    private final Distances[] distances;
    private final int index;
    private final Sample localSample;
    private final Sample example;
    private final CountDownLatch endController;

    public IndividualDistanceTask(Distances[] distances, int index, Sample localSample, Sample example, CountDownLatch endController) {
        this.distances = distances;
        this.index = index;
        this.localSample = localSample;
        this.example = example;
        this.endController = endController;
    }

    @Override
    public void run() {
        distances[index] = new Distances();
        distances[index].setIndex(index);
        distances[index].setDistances(EuclideanDistanceCalulator.calculate(localSample, example));
        endController.countDown();
    }
}
