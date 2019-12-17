package com.springboot.workflow.stateflow.state.impl;

import com.springboot.workflow.repository.TakeLeaveRep;
import com.springboot.workflow.stateflow.statecontext.StateContext;
import com.springboot.workflow.entity.TakeLeave;
import com.springboot.workflow.stateflow.state.TakeLeaveState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 审核结束状态
 */
public class AuditOverState implements TakeLeaveState {
    private static Logger logger = LogManager.getLogger(DepManagerState.class);

    private TakeLeaveRep takeLeaveRep;

    public AuditOverState(TakeLeaveRep takeLeaveRep) {
        this.takeLeaveRep = takeLeaveRep;
    }

    @Override
    public void handle(StateContext stateContext) {
        TakeLeave takeLeave = (TakeLeave) stateContext.getBusinessObj();
        takeLeave.setCurrentState("审核结束");
        System.out.println("审核结束:" + takeLeave.getCurrentState());
    }
}
