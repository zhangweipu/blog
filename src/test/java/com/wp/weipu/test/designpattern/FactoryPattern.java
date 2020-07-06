package com.wp.weipu.test.designpattern;

/**
 * 工场模式，设置一个接口，几个实现类，在加一个工场类
 */
interface car {
    void run();
}

class SmallCar implements car {

    @Override
    public void run() {
        System.out.println("run slowly");
    }
}

class BigCar implements car {
    @Override
    public void run() {
        System.out.println("run fast");
    }
}

public class FactoryPattern {
    public car getInstance(String dataType) {
        if ("smallCar".equals(dataType)) {
            return new BigCar();
        } else if ("bigCar".equals(dataType)) {
            return new SmallCar();
        }
        return null;
    }
}
