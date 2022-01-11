package com.gxitsky.controller;

import com.gxitsky.service.TakeLeaveService;
import com.gxitsky.entity.TakeLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/takeLeave")
public class TakeLeaveController {

    @Autowired
    private TakeLeaveService takeLeaveService;

    @PostMapping("/save")
    public TakeLeave takeLeave(TakeLeave takeLeave){
        takeLeaveService.save(takeLeave);
        return takeLeave;
    }

}
