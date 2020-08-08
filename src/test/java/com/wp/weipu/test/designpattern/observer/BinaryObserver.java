package com.wp.weipu.test.designpattern.observer;

public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        //循环引用了吧
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("binary String" + subject.getState());
    }
}
