package com.gxitsky.stateflow.state.impl;

import com.gxitsky.stateflow.context.StateContext;
import com.gxitsky.repository.TakeLeaveRep;
import com.gxitsky.entity.TakeLeave;
import com.gxitsky.stateflow.state.TakeLeaveState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 具体状态
 * 部门经理审核
 */
public class DepManagerState implements TakeLeaveState {
    private static Logger logger = LogManager.getLogger(DepManagerState.class);

    private TakeLeaveRep takeLeaveRep;

    public DepManagerState(TakeLeaveRep takeLeaveRep) {
        this.takeLeaveRep = takeLeaveRep;
    }

    @Override
    public void handle(StateContext stateContext) {
        TakeLeave takeLeave = (TakeLeave) stateContext.getBusinessObj();
        if ("Y".equals(takeLeave.getCurrentState())) {
            System.out.println("部门经理审批：同意");
            //同意
            stateContext.setState(new AuditOverState(takeLeaveRep));
            stateContext.doWork();
        } else {
            System.out.println("部门经理审批：不同意");
            //不同意,结束
            stateContext.setState(new AuditOverState(takeLeaveRep));
            stateContext.doWork();
        }
    }
}
