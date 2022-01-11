package com.gxitsky.aircondition.stateimpl;

import com.gxitsky.aircondition.IState;
import com.gxitsky.aircondition.OperateEvent;
import com.gxitsky.aircondition.StateContext;

/**
 * 送风中状态
 */
public class FanOnlyState implements IState {
    @Override
    public void handle(StateContext context, OperateEvent event) {
        switch (event) {
            case CLICK_POWER:
                //下一状态:送风
                context.setState(new OffState());
                doStopFan();
                break;
            case CLICK_COOL:
                //下一状态:制冷
                context.setState(new CoolState());
                doStartCool();
                break;
        }
    }

    private void doStopFan() {
        System.out.println("关闭送风");
    }
    private void doStartCool() {
        System.out.println("开始制冷");
    }

}
