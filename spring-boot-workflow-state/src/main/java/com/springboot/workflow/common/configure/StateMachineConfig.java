package com.springboot.workflow.common.configure;

import com.springboot.workflow.common.enumclass.Events;
import com.springboot.workflow.common.enumclass.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine//开启状态机
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    /**
     * 状态机配置,自动启动,添加监听器
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config
                .withConfiguration()
                    .autoStartup(true)
                    .listener(listener());
    }

    /**
     * 状态初始化
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                    .initial(States.SI)
                    .states(EnumSet.allOf(States.class));
    }

    /**
     * 状态迁移
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(States.SI).target(States.S1).event(Events.E1)
                    .and()
                .withExternal()
                    .source(States.S1).target(States.S2).event(Events.E2);
    }

    /**
     * 装态监听器
     *
     * @return
     */
    @Bean
    public StateMachineListener<States, Events> listener() {
        StateMachineListenerAdapter<States, Events> listenerAdapter = new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
        return listenerAdapter;
    }
}
