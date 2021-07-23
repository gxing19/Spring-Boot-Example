package com.springboot.example.demo.designpattern.observer;

/**
 * 具体观察者A
 */
public class ConcreteObserverB implements IObserver {

    @Override
    public void response() {
        System.out.println("具体观察者B作出响应......");
    }
}
