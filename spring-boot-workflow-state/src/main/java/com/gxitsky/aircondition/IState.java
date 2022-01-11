package com.gxitsky.aircondition;

/**
 * 抽象状态接口
 */
public interface IState {
    void handle(StateContext stateContext, OperateEvent event);
}
