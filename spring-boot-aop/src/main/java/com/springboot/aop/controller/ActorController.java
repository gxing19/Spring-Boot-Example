package com.springboot.aop.controller;

import com.springboot.aop.common.bean.ResultBean;
import com.springboot.aop.common.bean.ResultHelper;
import com.springboot.aop.entity.Actor;
import com.springboot.aop.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private static final Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    @GetMapping("/list")
    public ResultBean listActor() {
        List<Actor> actorList = actorService.listActor();
        return ResultHelper.success(actorList);
    }

    @GetMapping("/getByActorId")
    public ResultBean getByActorId(Long actorId) {
        Actor actor = actorService.getByActorId(actorId);
        return ResultHelper.success(actor);
    }

}
