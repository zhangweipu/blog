package com.wp.weipu.test.juclearn;


import org.junit.Test;
import org.junit.experimental.theories.Theories;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//AQS示例,这个类一次只能被一个线程持有
public class Metux2 implements Lock, Serializable {

    private static final Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {

        //重写这几个方法有什么用
        //note:state=0表示没有线程等待，大于0说明有n个线程在等待资源
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                //设置当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (getState() == 1) {
                setState(0);
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        // 抽象类里没实现
        @Override
        protected boolean isHeldExclusively() {
            //1有一个线程处于等待状态
            if (getState() == 1) {
                return true;
            }
            return false;
        }

        //这个的做用
        Condition newCondition() {
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        //我不能new 一个新的ConditionObject()
        return sync.newCondition();
    }

    @Test
    public void test() {
        //声明一个不可重入锁
//        Metux2 lock = new Metux2();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                lock.lock();
                try {
                    System.out.println("thread1 running now");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //线程中断
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                } finally {
                    //释放锁
//                    lock.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                lock.lock();
                try {
                    System.out.println("thread2 running now");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
//                    lock.unlock();
                }
            }
        }).start();
    }
}
