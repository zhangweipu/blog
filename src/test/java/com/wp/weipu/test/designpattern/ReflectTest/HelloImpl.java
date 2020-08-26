package com.wp.weipu.test.designpattern.ReflectTest;

public class HelloImpl implements HelloInterface {
    @Override
    public String helloWorld(String name) {
        System.out.println(name + ": hello world");
        return null;
    }
}
