package com.wp.weipu.test.iocHand;


import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/***
 * 这个人用c++实现的
 */
public class HTContainer implements IHTContainer {

    //使用一个map来存储注册的对象
    private Map<String, Class> objectMap = new HashMap<>();

    //注册方法，把类注册到容器
    public void registerObject(Class clzz) {
        this.objectMap.put(clzz.getName(), clzz);
    }

    //通过传递的对象进行匹配
    public Object resolve(Class clzz) {
        String key = clzz.getName();
//        return Proxy.newProxyInstance()
        return null;
    }
}
