package com.springboot.example.demo.designpattern.observer.other;

public class ConcreteObserverA extends AbstractObserver {

    public ConcreteObserverA(Subject subject) {
        this.subject = subject;
        //主动加入监听列表
        this.subject.add(this);
    }

    @Override
    public void update() {
        System.out.println("具体观察者A：" + subject.getState());
    }
}
