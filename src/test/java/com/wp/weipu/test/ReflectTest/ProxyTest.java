package com.wp.weipu.test.ReflectTest;

public class ProxyTest {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        HelloProxy s = new HelloProxy();
        HelloInterface helloInterface = (HelloInterface) s.bind(hello);
        helloInterface.helloWorld("tom");
    }
}
