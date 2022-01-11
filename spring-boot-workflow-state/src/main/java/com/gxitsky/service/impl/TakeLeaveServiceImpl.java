package com.gxitsky.service.impl;

import com.gxitsky.entity.TakeLeave;
import com.gxitsky.repository.TakeLeaveRep;
import com.gxitsky.stateflow.context.TakeLeaveStateContext;
import com.gxitsky.stateflow.state.impl.ProjectManagerState;
import com.gxitsky.service.TakeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TakeLeaveServiceImpl implements TakeLeaveService {

    @Autowired
    private TakeLeaveRep takeLeaveRep;

    @Override
    public void save(TakeLeave takeLeave) {
        TakeLeaveStateContext context = new TakeLeaveStateContext();
        context.setBusinessObj(takeLeave);
        //配置上下文的初始状态，以后就随流程而动态变化（状态驱动）
        context.setState(new ProjectManagerState(takeLeaveRep));
        context.doWork();
    }
}
