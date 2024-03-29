package com.gxitsky.designpattern.observer.other;

/**
 * 观察者抽象
 */
public abstract class AbstractObserver {

    protected Subject subject;

    public abstract void update();
}
