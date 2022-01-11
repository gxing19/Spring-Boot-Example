package com.gxitsky.designpattern.observer;

public class ObserverMain {

    public static void main(String[] args) {
        //目标对象
        AbstractSubject subject = new ConcreteSubject();
        //维护观察者
        subject.add(new ConcreteObserverA());
        subject.add(new ConcreteObserverB());
        //发起通知
        subject.noticeObserver();
    }
}
