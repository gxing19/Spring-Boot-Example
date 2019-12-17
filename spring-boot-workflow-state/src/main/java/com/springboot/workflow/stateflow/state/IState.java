package com.springboot.workflow.stateflow.state;

import com.springboot.workflow.stateflow.statecontext.StateContext;

/**
 * 状态抽象接口
 */
public interface IState {

    /**
     * 执行状态处理
     * @param stateContext
     */
    void handle(StateContext stateContext);
}
