package com.springboot.demo.designpattern.observer.other;

public class ObserverMain1 {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new ConcreteObserverA(subject);
        new ConcreteObserverB(subject);

        subject.notifyAllObservers();

        subject.setState(1001);
    }
}
