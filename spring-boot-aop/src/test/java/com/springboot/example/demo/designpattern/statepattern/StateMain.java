package com.springboot.example.demo.designpattern.statepattern;

public class StateMain {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
//        context.setState(new ConcreteStateA());
        context.handle();
        context.handle();
        context.handle();
        context.handle();

    }
}
