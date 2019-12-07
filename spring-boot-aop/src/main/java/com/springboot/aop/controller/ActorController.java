package com.springboot.aop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @GetMapping("/listByPage")
    public ResultBean listByPage(Actor actor) {
        List<Actor> actorList = actorService.listByPage(actor);
        return ResultHelper.success(actorList);
    }
    @GetMapping("/getByPage")
    public ResultBean getByPage(Actor actor) {
        List<Actor> actorList = actorService.getByPage(actor);
        return ResultHelper.success(actorList);
    }


    @GetMapping("/getByActorId")
    public ResultBean getByActorId(Long id) {
        Actor actor = actorService.getByActorId(id);
        return ResultHelper.success(actor);
    }

}
