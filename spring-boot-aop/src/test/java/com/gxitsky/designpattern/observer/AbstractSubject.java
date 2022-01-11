package com.gxitsky.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者抽象(抽象目标)
 */
public abstract class AbstractSubject {

    protected List<IObserver> observerList = new ArrayList<>();

    /**
     * 添加观察者
     *
     * @param observer
     */
    public void add(IObserver observer) {
        observerList.add(observer);
    }

    /**
     * 移除观察者
     *
     * @param observer
     */
    public void remove(IObserver observer) {
        observerList.remove(observer);
    }

    /**
     * 抽象通知方法
     */
    public abstract void noticeObserver();
}
