package com.wp.weipu.test.juclearn.threadpool;

public class RunnableDenyPolicy extends RuntimeException {
    public RunnableDenyPolicy(String message) {
        super(message);
    }
}
