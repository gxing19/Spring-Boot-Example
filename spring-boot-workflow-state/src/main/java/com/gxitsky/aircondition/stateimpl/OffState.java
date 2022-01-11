package com.gxitsky.aircondition.stateimpl;

import com.gxitsky.aircondition.IState;
import com.gxitsky.aircondition.OperateEvent;
import com.gxitsky.aircondition.StateContext;

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
