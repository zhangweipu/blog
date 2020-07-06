package com.wp.weipu.test.ReflectTest;

public class ProxyTest {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        HelloProxy s = new HelloProxy();
        //jdk动态代理机制只能代理实现了接口的类
        //cglib是针对类来实现代理的。他的原理是对指定的目标类
        //生成一个子类，并覆盖其中方法实现增强，
        //因为采用继承没所以不能对final修饰的类进行代理
        HelloInterface helloInterface = (HelloInterface) s.bind(hello);
        helloInterface.helloWorld("tom");
    }
}
