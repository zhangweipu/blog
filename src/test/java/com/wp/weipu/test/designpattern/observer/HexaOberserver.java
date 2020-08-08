package com.wp.weipu.test.designpattern.observer;

public class HexaOberserver extends Observer {

    public HexaOberserver(Subject subject) {
        this.subject = subject;
        //循环引用了吧
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("hexa String" + subject.getState());
    }
}
