package com.gxitsky.stateflow.state;

import com.gxitsky.stateflow.context.StateContext;

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
