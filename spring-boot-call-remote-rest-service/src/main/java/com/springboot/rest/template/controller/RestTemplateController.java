package com.springboot.rest.template.controller;


import com.springboot.rest.template.entity.Actor;
import com.springboot.rest.template.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {

    @Autowired
    private RestTemplateService restTemplateService;

    @RequestMapping("/hello")
    public String returnHello(){
        return "Hello World";
    }

    @RequestMapping("/queryAll")
    public List<Actor> queryAll(){
        return restTemplateService.queryAll();
    }

    @RequestMapping("/queryByActorId")
    public Actor queryByActorId(Long actorId){
        return restTemplateService.queryByActorId(actorId);
    }


    @RequestMapping("/add")
    public int addActor(Actor actor) throws URISyntaxException {
        return restTemplateService.addActor(actor);

    }

    @RequestMapping("/queryCount")
    public int queryCount(){
        return  restTemplateService.queryCount();
    }
}
