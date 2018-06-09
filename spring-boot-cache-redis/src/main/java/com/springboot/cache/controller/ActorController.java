package com.springboot.cache.controller;

import com.springboot.cache.entity.Actor;
import com.springboot.cache.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/actor")
public class ActorController {
    private final static Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    @RequestMapping("/queryById")
    public Actor queryById(Long actorId){
        Actor acotr = actorService.queryById(actorId);
        return acotr;
    }

    @RequestMapping("/queryByActorId")
    public Actor queryByActorId(Long actorId){
        Actor acotr = actorService.queryByActorId(actorId);
        return acotr;
    }



}
