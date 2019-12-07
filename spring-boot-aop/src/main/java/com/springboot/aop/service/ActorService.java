package com.springboot.aop.service;

import com.springboot.aop.entity.Actor;

import java.util.List;

public interface ActorService {
    Actor getByActorId(Long id);

    List<Actor> listByPage(Actor actor);

    List<Actor> getByPage(Actor actor);
}
