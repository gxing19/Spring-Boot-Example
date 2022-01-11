package com.gxitsky.service;

import com.gxitsky.entity.Actor;

import java.util.List;

public interface ActorService {

    int addActor(Actor actor);

    int updateActor(String firstName, Long actorId);

    int deleteActor(Long actorId);

    Actor queryByActorId(Long actorId);

    int queryCount();

    int queryCountByLastName(String lastName);

    String queryLastName(Long actorId);

    List<Actor> queryActorList();
}
