package com.springboot.workflow.common.component;

import com.springboot.workflow.common.enumclass.Events;
import com.springboot.workflow.common.enumclass.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class StateMachineStart implements CommandLineRunner {

    @Resource
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);

    }
}
