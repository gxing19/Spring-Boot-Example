package com.springboot.jpa.service;

import com.springboot.jpa.entity.Actor;

import java.util.List;

public interface ActorService {
    Actor addActor(Actor actor);

    void deleteByActorId(Long actorId);

    List<Actor> queryAll();

    Actor queryByActorId(Long actorId);

    Long countActor();

    Long countBy(Actor actor);

    List<Actor> queryByFirstName(String firstName);

    int updateFirstName(Long actorId, String firstName);
}
