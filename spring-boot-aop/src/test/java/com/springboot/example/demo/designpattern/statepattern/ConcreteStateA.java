package com.springboot.example.demo.designpattern.statepattern;

public class ConcreteStateA implements IState {

    @Override
    public void handle(Context context) {
        System.out.println("当前状态：A");
        //状态 A 执行完后，迁移到状态 B
        context.setState(new ConcreteStateB());
    }
}
