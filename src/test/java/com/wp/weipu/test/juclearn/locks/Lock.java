package com.wp.weipu.test.juclearn.locks;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 自定义锁
 * 自定义锁，解决sychronized关键字 的不能控制阻塞时长，以及无法中断的缺点
 */
public interface Lock {
    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();

}
