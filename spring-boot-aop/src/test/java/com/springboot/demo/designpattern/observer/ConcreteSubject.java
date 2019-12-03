package com.springboot.demo.designpattern.observer;

public class ConcreteSubject extends AbstractSubject {

    /**
     * 具体观察者发生改变
     */
    public void noticeObserver() {
        System.out.println("具体观察者发生改变......");
        System.out.println("-----------------------");

        for (IObserver observer : observerList) {
            observer.response();
        }
    }
}
