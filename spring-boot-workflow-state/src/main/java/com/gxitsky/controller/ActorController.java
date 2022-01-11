package com.gxitsky.controller;

import com.gxitsky.repository.ActorRep;
import com.gxitsky.entity.Actor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private static final Logger logger = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorRep actorRep;

    @GetMapping("/getById")
    public Actor getById(Integer id) {
        Actor actor = actorRep.findById(id).get();
        return actor;
    }

}
