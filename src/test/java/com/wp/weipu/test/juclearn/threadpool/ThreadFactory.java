package com.wp.weipu.test.juclearn.threadpool;

//实现带有这个接口的实现类，可以实现和lambda有关的操作
@FunctionalInterface
public interface ThreadFactory {
    /**
     * 定制化线程
     *
     * @param runnable
     * @return
     */
    Thread createThread(Runnable runnable);
}
