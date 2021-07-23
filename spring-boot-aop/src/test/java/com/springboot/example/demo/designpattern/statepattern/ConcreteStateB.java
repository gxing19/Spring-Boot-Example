package com.springboot.example.demo.designpattern.statepattern;

public class ConcreteStateB implements IState {

    @Override
    public void handle(Context context) {
        System.out.println("当前状态：B");
        //状态 B 执行完后，迁移到状态 A
        context.setState(new ConcreteStateA());
    }
}
