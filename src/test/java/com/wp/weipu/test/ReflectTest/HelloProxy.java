package com.wp.weipu.test.ReflectTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloProxy implements InvocationHandler {

    private Object proxy;

    /**
     * 绑定对象
     * 使用构造函数也可以
     *
     * @param proxy
     * @return
     */
    public Object bind(Object proxy) {
        this.proxy = proxy;
        return Proxy.newProxyInstance(this.proxy.getClass().getClassLoader(), this.proxy.getClass().getInterfaces(),
                this);
    }

    //invoke在什么时候使用的

    /**
     * 执行目标对象的方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //目的是在这里，增加自己的操作
        System.out.println("start say hello");
        Object result = method.invoke(this.proxy, args);
        return result;
    }
}
