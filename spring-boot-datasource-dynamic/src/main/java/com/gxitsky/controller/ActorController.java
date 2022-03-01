package com.gxitsky.controller;

import com.gxitsky.entity.Actor;
import com.gxitsky.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void saveActorList(Boolean errorFlag) {
        actorService.saveActorList(errorFlag);
    }
}
