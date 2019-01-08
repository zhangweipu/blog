package com.wp.weipu.test.threadandrunnable.searchfile;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParallelGroupSearch {
    public static void search(File file, String fileName, Result result) {
        File[] contents = file.listFiles();
        ConcurrentLinkedQueue<File> directories = new ConcurrentLinkedQueue<>();
        for (File f : contents) {
            if (f.isDirectory()) {
                directories.add(f);
            }
        }
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
        ParallelGroupFileTask[] tasks = new ParallelGroupFileTask[numThreads];
        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new ParallelGroupFileTask(fileName, directories, result);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        boolean finish = false;
        int numFinish = 0;

        while (!finish) {
            numFinish = 0;
            for (int i = 0; i < threads.length; i++) {
                if (threads[i].getState() == Thread.State.TERMINATED) {
                    numFinish++;
                    if (tasks[i].getFound()) {
                        finish = true;
                    }
                }
            }
            if (numFinish == threads.length) {
                finish = true;
            }
            if (numFinish != threads.length) {
                for (Thread thread : threads) {
                    thread.interrupt();
                }
            }
        }
    }
}
