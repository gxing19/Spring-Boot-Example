package com.gxitsky.stateflow.context;

import com.gxitsky.stateflow.state.IState;
import lombok.Data;

/**
 * 状态上下文
 */
@Data
public class StateContext {

    private IState state;

    private Object businessObj;

    public void doWork(){
        this.state.handle(this);
    }
}
