package com.gxitsky.aircondition;

import com.gxitsky.aircondition.stateimpl.OffState;

public class MainTest {
    public static void main(String[] args) {
        //初始状态
        IState initState = new OffState();
        StateContext context = new StateContext(initState);
        context.doWork(OperateEvent.CLICK_POWER);//开始送风
        context.doWork(OperateEvent.CLICK_COOL);//开始制冷
        context.doWork(OperateEvent.CLICK_COOL);//开始送风
        context.doWork(OperateEvent.CLICK_POWER);//关闭送风
    }
}
