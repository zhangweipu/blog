package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 交替打印 0，奇数，偶数
 */
public class ZeroEvenOdd {
    private int n;
    //是否零阻塞
    private boolean flag = false;
    private int count = 0;
    Lock lock = new ReentrantLock();
    //计数器
    private Condition zero = lock.newCondition();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();

    final ReentrantLock lock1 =new ReentrantLock();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) {
        lock.lock();
        try {
            for (int i = 1; i <= n; i++) {
                while (flag) {
                    zero.await();
                }
                //和lamdba表达式有关
                printNumber.accept(0);
                count++;
                flag = !flag;
                if (i % 2 == 0) {
                    even.signalAll();
                } else {
                    odd.signalAll();
                }
            }
            System.out.println("0解锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 偶数
     *
     * @param printNumber
     */
    public void even(IntConsumer printNumber) {
        lock.lock();
        try {
            for (; count < n; ) {
                if (n == 1) {
                    break;
                }
                if (n > 1) {
                    //最后一个数,是奇数就跳出循环
                    if (n % 2 != 0 && count == n - 1) {
                        break;
                    }
                }
                //不是最后一个数，并且是奇数
                while (!flag || count % 2 != 0) {
                    //这是时候释放资源了，但是线程还没结束
                    even.await();
                }
                //是偶数，开始打印0
                printNumber.accept(count);
                flag = !flag;
                zero.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("解锁偶数");
        }
    }

    /**
     * 奇数
     */
    public void odd(IntConsumer printNumber) {
        lock.lock();
        try {
            if (count == 1 && n == 1) {
                printNumber.accept(count);
            }
            for (; count < n; ) {
                if (n > 2) {
                    //这时候的count是奇数才对啊
                    if (n % 2 == 0 && count == n - 1) {
                        break;
                    }
                }
                while (!flag || count % 2 == 0) {
                    odd.await();
                }
                printNumber.accept(count);
                flag = !flag;
                zero.signalAll();
                if (n == 2) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(4);
        new Thread(() -> {
            try {
                //x是什么
                zeroEvenOdd.zero((x) -> {
                    System.out.println(x + " " + Thread.currentThread().getName());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "zero").start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even((x) -> {
                    System.out.println(x + " " + Thread.currentThread().getName());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "even").start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd((x) -> {
                    System.out.println(x + " " + Thread.currentThread().getName());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "odd").start();


        zeroEvenOdd.test2();
    }


    public void test(IntConsumer s) {
        s.accept(1);
        System.out.println(s);
    }

    public void test2() {
        test((x) -> {
            System.out.println(x);
        });
    }
}
