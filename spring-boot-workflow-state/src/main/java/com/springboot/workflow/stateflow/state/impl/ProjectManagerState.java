package com.springboot.workflow.stateflow.state.impl;

import com.springboot.workflow.repository.TakeLeaveRep;
import com.springboot.workflow.stateflow.statecontext.StateContext;
import com.springboot.workflow.entity.TakeLeave;
import com.springboot.workflow.stateflow.state.TakeLeaveState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * 具体状态
 * 项目经理审核
 */
public class ProjectManagerState implements TakeLeaveState {
    private static final Logger logger = LogManager.getLogger(ProjectManagerState.class);

    private TakeLeaveRep takeLeaveRep;

    public ProjectManagerState() {
    }

    public ProjectManagerState(TakeLeaveRep takeLeaveRep) {
        this.takeLeaveRep = takeLeaveRep;
    }

    @Override
    public void handle(StateContext stateContext) {
        TakeLeave takeLeave = (TakeLeave) stateContext.getBusinessObj();
        System.out.println(takeLeave.getUsername() + "申请从" + takeLeave.getStartDate() + "开始请假" + takeLeave.getLeaveDays() + "天，请项目经理审核。");
        System.out.println("项目经理审核中......");
        if ("Y".equals(takeLeave.getCurrentState())) {
            System.out.println("项目经理审批：" + "同意");
            if (takeLeave.getLeaveDays() > 3) {
                System.out.println("请假超过3天,转交部门经理审批......");
                //请假大于3天转交上级部门经理审批
                stateContext.setState(new DepManagerState(takeLeaveRep));
                stateContext.doWork();
            } else {
                //3天及以内,同意后结束
                stateContext.setState(new AuditOverState(takeLeaveRep));
                stateContext.doWork();
            }
        } else {
            System.out.println("项目经理审批：" + "不同意");
            //不同意,强求束
            stateContext.setState(new AuditOverState(takeLeaveRep));
            stateContext.doWork();
        }
    }
}
