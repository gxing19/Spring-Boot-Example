package com.gxitsky.service;

import com.gxitsky.entity.Actor;

import java.util.List;

public interface ActorService {
    Actor getById(Long id);

    Actor save(Actor actor);

    List<Actor> getActor(Actor actor);

    void saveActorList(Boolean errorFlag);
}
