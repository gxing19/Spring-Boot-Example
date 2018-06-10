package com.springboot.nosql.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springboot.nosql.entity.Actor;

import java.util.List;

public interface ActorService {

    void saveActor(Actor actor);

    Actor queryByActorId(Long actorId);

    List<Actor> queryByFirstName(String firstName);

    UpdateResult updateActor(Actor actor);

    DeleteResult deleteByActorId(Long actorId);
}
