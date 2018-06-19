package com.springboot.admin.controller;

import com.springboot.admin.entity.Actor;
import com.springboot.admin.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @RequestMapping("/queryByActorId")
    public Actor queryByActorId(Long actorId){

        return actorRepository.findById(actorId).get();
    }
}
