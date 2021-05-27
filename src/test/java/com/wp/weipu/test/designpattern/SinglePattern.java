package com.wp.weipu.test.designpattern;

public class SinglePattern {
    //懒汉模式，等用的时候才实例化一个对象
    private static SinglePattern instance = null;

    private SinglePattern() {
    }

    public static SinglePattern getInstance() {
        if (instance == null) {
            instance = new SinglePattern();
        }
        //用的时候初始化
        return instance;
    }
}

class SinglePatternSyn {
    //双重加锁，是这样的吗 双重if判断
    private volatile static SinglePatternSyn instance = null;

    private SinglePatternSyn() {
    }

    public static SinglePatternSyn getInstance() {
        if (instance == null) {
            synchronized (SinglePatternSyn.class) {
                instance = new SinglePatternSyn();
            }
        }
        return instance;
    }

}

class ESinglePattern {
    //饿汉模式
    private static final ESinglePattern instance = new ESinglePattern();

    public static ESinglePattern getInstance() {
        return instance;
    }
}

class StaticSingle {
    //静态模式，保证线程安全，因为静态类在对象加载时就加载了，只会加载一次，由jvm保证安全
    private static class StaticSingleDe {
        private static final StaticSingle staticSingle = new StaticSingle();
    }

    private StaticSingle() {
    }

    public static StaticSingle getInstance() {
        return StaticSingleDe.staticSingle;
    }
}
//最后一种是枚举方法。。
