package com.gxitsky.designpattern.observer;

/**
 * 具体观察者A
 */
public class ConcreteObserverA implements IObserver {

    @Override
    public void response() {
        System.out.println("具体观察者A作出响应......");
    }
}
