package com.wp.weipu.test.juclearn.threadpool;

public class InternalTask implements Runnable {
    //任务队列
    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前任务，主要在线程池的shutdown中使用
     */
    public void stop() {
        this.running = false;
    }
}
