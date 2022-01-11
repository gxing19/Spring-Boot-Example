package com.gxitsky.aircondition.stateimpl;

import com.gxitsky.aircondition.IState;
import com.gxitsky.aircondition.OperateEvent;
import com.gxitsky.aircondition.StateContext;

/**
 * 制冷中状态
 */
public class CoolState implements IState {

    @Override
    public void handle(StateContext stateContext, OperateEvent event) {
        switch (event) {
            case CLICK_POWER:
                stateContext.setState(new OffState());
                doStopCool();
                break;
            case CLICK_COOL:
                stateContext.setState(new FanOnlyState());
                doStartFan();
                break;
        }
    }

    private void doStartFan() {
        System.out.println("开始送风");
    }
    private void doStopCool(){
        System.out.println("关闭制冷");
    }
}
