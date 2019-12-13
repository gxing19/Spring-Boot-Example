package com.springboot.datasource.service;

import com.springboot.datasource.entity.Actor;
import org.apache.catalina.User;

import java.util.List;

public interface ActorService {
    Actor getById(Long id);

    Actor save(Actor actor);

    List<Actor> getActor(Actor actor);

    void saveActorList();
}
