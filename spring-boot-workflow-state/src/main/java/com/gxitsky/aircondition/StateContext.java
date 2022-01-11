package com.gxitsky.aircondition;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 状态上下文
 */
@Data
@Accessors(chain = true)
public class StateContext {

    private IState state;

    private Object businessObj;

    public StateContext() {
    }

    public StateContext(IState state) {
        this.state = state;
    }

    public void doWork(OperateEvent event) {
        this.state.handle(this, event);
    }

}
