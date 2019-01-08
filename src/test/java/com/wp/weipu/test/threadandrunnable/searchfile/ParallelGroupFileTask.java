package com.wp.weipu.test.threadandrunnable.searchfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParallelGroupFileTask implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(ParallelGroupFileTask.class);

    private final String fileName;
    private final ConcurrentLinkedQueue<File> directories;
    private final Result parallelResult;
    private boolean found;

    public ParallelGroupFileTask(String fileName, ConcurrentLinkedQueue<File> directories, Result parallelResult) {
        this.fileName = fileName;
        this.directories = directories;
        this.parallelResult = parallelResult;
    }

    @Override
    public void run() {
        while (directories.size() > 0) {
            File file = directories.poll();
            try {
                processDirectory(file, fileName, parallelResult);
                if (found) {
                    logger.info("找到了", parallelResult.getFilePath());
                }
            } catch (InterruptedException e) {
                logger.info("线程中断一次");
            }
        }
    }

    private void processDirectory(File file, String fileName, Result parallelResult) throws InterruptedException {
        File[] contents;
        contents = file.listFiles();
        if (contents == null || contents.length == 0) {
            return;
        }
        for (File f : contents) {
            if (f.isDirectory()) {
                processDirectory(f, fileName, parallelResult);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                if (found) {
                    return;
                }
            } else {
                //不是目录是文件
                processFile(f, fileName, parallelResult);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                if (found) {
                    return;
                }
            }
        }

    }

    private void processFile(File contents, String fileName, Result result) {
        if (contents.getName().equals(fileName)) {
            result.setFound(true);
            result.setFilePath(contents.getAbsolutePath());
            found = true;
        }
    }

    public boolean getFound() {
        return found;
    }
}
