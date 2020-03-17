package com.wp.weipu.test.ReflectTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloProxy implements InvocationHandler {

    private Object proxy;

    public Object bind(Object proxy) {
        this.proxy = proxy;
        return Proxy.newProxyInstance(this.proxy.getClass().getClassLoader(), this.proxy.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start say hello");
        Object result = method.invoke(this.proxy, args);
        return result;
    }
}
