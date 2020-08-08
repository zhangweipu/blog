package com.wp.weipu.test.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被用于观察的目标类
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
