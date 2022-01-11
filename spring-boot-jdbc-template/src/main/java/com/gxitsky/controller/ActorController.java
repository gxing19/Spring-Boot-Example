package com.gxitsky.controller;

import com.gxitsky.entity.Actor;
import com.gxitsky.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/actor")
public class ActorController {
    private final static Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    @RequestMapping("/add")
    public int addActor(Actor actor) {
//        Actor actor = new Actor()
//                .setActorId(20L).setLastUpdate(new Date())
//                .setFirstName("Hello").setLastName("Kitty");
        actor.setLastUpdate(new Date());
        return actorService.addActor(actor);
    }

    @RequestMapping("/update")
    public int updateActor(String firstName, Long actorId) {
        return actorService.updateActor(firstName, actorId);
    }

    @RequestMapping("/delete")
    public int delete(Long actorId) {
        return actorService.deleteActor(actorId);
    }

    @RequestMapping("/queryCount")
    public int queryCount() {
        return actorService.queryCount();
    }

    @RequestMapping("queryCountByLastName")
    public int queryCountByLastName(String lastName) {
        return actorService.queryCountByLastName(lastName);
    }

    @RequestMapping("queryLastName")
    public String queryLastName(Long actorId) {
        return actorService.queryLastName(actorId);
    }

    @RequestMapping("/queryByActorId")
    public Actor queryById(Long actorId) {
        return actorService.queryByActorId(actorId);
    }

    @RequestMapping("/queryActorList")
    public List<Actor> queryActorList() {
        return actorService.queryActorList();
    }
}
