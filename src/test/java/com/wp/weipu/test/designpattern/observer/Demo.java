package com.wp.weipu.test.designpattern.observer;

public class Demo {

    public static void main(String[] args) {
        Subject subject = new Subject();
        //注册到目标类
        new BinaryObserver(subject);
        new HexaOberserver(subject);
        subject.setState(22);
    }
}
