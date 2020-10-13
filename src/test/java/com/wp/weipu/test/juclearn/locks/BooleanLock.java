package com.wp.weipu.test.juclearn.locks;

import java.util.*;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {
    //当前拥有锁的线程
    private Thread currentThread;
    private boolean locked = false;
    //存储进入阻塞状态的线程
    private final List<Thread> blockList = new ArrayList<>();

    private Thread currentThread() {
        return Thread.currentThread();
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                blockList.add(currentThread());
                this.wait();
            }
            //没添加吧
            blockList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                //这表示不设置时间的吗
                this.lock();
            } else {
                long remainingMills = mills;
                long endMill = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during");
                    }
                    //如果不在队列中，就不用
                    if (!blockList.contains(currentThread())) {
                        blockList.add(currentThread());
                        this.wait();
                        //使用while计时
                        remainingMills = endMill - System.currentTimeMillis();
                    }
                    blockList.remove(currentThread());
                    this.locked = true;
                    //这个有点多余啊
                    this.currentThread = currentThread();
                }
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == currentThread()) {
                this.locked = false;
                //java8的
                Optional.of(currentThread().getName() + "release the lock").ifPresent(System.out::print);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        //这是线程安全的实现吗
        return Collections.unmodifiableList(blockList);
    }

    public static void main(String[] args) {
        BooleanLock lock = new BooleanLock();
        try {
            lock.lock();
            //doSomething()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
