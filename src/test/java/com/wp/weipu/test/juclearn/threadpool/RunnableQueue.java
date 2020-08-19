package com.wp.weipu.test.juclearn.threadpool;

/**
 * 任务队列，缓存提交的线程池的任务
 */
public interface RunnableQueue {
    /**
     * 当有新任务时，会先存队列里面
     *
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程获取任务
     *
     * @return
     */
    Runnable take();

    /**
     * 任务队列中任务的数量
     *
     * @return
     */
    int size();
}
