package com.springboot.datasource.controller;

import com.springboot.datasource.common.datasource.DataSourceEnum;
import com.springboot.datasource.common.datasource.annotation.DataSourceSelector;
import com.springboot.datasource.entity.Actor;
import com.springboot.datasource.service.ActorService;
import lombok.Data;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private static final Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    @GetMapping("/getById")
    public Actor getById(Long id) {
        Actor actor = actorService.getById(id);
        return actor;
    }
    @GetMapping("/getActor")
    public List<Actor> getActor(Actor actor) {
        List<Actor> actorList = actorService.getActor(actor);
        return actorList;
    }

    @PostMapping("/save")
    public Actor save(Actor actor) {
        actor.setLastUpdate(new Date());
        actor = actorService.save(actor);
        return actor;
    }

    @PostMapping("/saveActorList")
    public void saveActorList() {
        actorService.saveActorList();
    }
}
