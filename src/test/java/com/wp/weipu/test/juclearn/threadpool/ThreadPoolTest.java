package com.wp.weipu.test.juclearn.threadpool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {

    public static void main(String[] args) {
        Set<Thread> cache = new HashSet<>();
        for (int i = 1; i < 4; i++) {
            cache.add(new Thread("thread-" + i));
        }
        for (int i = 0; i < 20; i++) {
            for (Thread t:cache){

            }
        }
    }
}
