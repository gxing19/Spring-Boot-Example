package com.springboot.aop.service;

import com.springboot.aop.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> listActor();

    Actor getByActorId(Long actorId);
}
