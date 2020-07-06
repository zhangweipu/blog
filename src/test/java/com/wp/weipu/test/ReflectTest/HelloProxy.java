package com.wp.weipu.test.ReflectTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloProxy implements InvocationHandler {

    private Object proxy;

    /**
     * 绑定对象
     * @param proxy
     * @return
     */
    public Object bind(Object proxy) {
        this.proxy = proxy;
        return Proxy.newProxyInstance(this.proxy.getClass().getClassLoader(), this.proxy.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //目的是在这里，增加自己的操作
        System.out.println("start say hello");
        Object result = method.invoke(this.proxy, args);
        return result;
    }
}
