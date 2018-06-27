package com.springboot.rest.template.service;

import com.springboot.rest.template.entity.Actor;

import java.net.URISyntaxException;
import java.util.List;

public interface RestTemplateService {

    List<Actor> queryAll();

    Actor queryByActorId(Long actorId);

    int addActor(Actor actor) throws URISyntaxException;

    int queryCount();


}
