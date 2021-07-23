package com.springboot.example.demo.designpattern.observer.other;

public class ConcreteObserverB extends AbstractObserver {

    public ConcreteObserverB(Subject subject) {
        this.subject = subject;
        //主动加入监听列表
        this.subject.add(this);
    }

    @Override
    public void update() {
        System.out.println("具体观察者B：" + subject.getState());
    }
}
