package com.wp.weipu.test.juclearn.threadpool;

/**
 * 实现一个线程池
 */
public interface ThreadPool {
    /**
     * 提交任务到线程池
     *
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取线程池初始大小
     *
     * @return
     */
    int getInitSize();

    /**
     * 获取线程池最大线程数
     *
     * @return
     */
    int getMaxSize();

    /**
     * 获取线程池线程核心数量
     *
     * @return
     */
    int getCoreSize();

    /**
     * 获取线程池缓存任务数
     *
     * @return
     */
    int getQueueSize();

    /**
     * 获取线程池活跃线程数量
     *
     * @return
     */
    int getActiveCount();

    /**
     * 判断线程池是否被关闭
     *
     * @return
     */
    boolean isShutdown();
}
