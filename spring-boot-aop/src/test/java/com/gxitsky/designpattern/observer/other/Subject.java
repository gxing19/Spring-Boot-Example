package com.gxitsky.designpattern.observer.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象目标对象(被观察者)
 */
public class Subject {
    //维护观察者容器
    private List<AbstractObserver> observerList = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    //状态改变,调用通知观察者方法
    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void add(AbstractObserver observer) {
        observerList.add(observer);
    }

    public void notifyAllObservers() {
        for (AbstractObserver observer : observerList) {
            observer.update();
        }
    }
}