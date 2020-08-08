package com.wp.weipu.test.designpattern.observer;

public abstract class Observer {
    /**
     * 目标类
     */
    protected Subject subject;

    public abstract void update();
}
