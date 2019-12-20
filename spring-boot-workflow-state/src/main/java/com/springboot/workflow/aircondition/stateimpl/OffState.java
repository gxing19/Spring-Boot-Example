package com.springboot.workflow.aircondition.stateimpl;

import com.springboot.workflow.aircondition.IState;
import com.springboot.workflow.aircondition.OperateEvent;
import com.springboot.workflow.aircondition.StateContext;

/**
 * 关闭中状态
 */
public class OffState implements IState {
    @Override
    public void handle(StateContext context, OperateEvent event) {
        switch (event){
            case CLICK_POWER:
                //进入下一个状态
                context.setState(new FanOnlyState());
                doStartFan();
                break;
        }

    }

    private void doStartFan() {
        System.out.println("开始送风");
    }
}
