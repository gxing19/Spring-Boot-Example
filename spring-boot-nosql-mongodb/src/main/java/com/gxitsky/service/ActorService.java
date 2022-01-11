package com.gxitsky.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.gxitsky.entity.Actor;

import java.util.List;

public interface ActorService {

    void saveActor(Actor actor);

    void saveActorList(List<Actor> actorList);

    UpdateResult addNXupdateEX(Actor actor);

    Actor queryByActorId(Long actorId);

    List<Actor> queryByFirstName(String firstName);

    UpdateResult updateActor(Actor actor);

    DeleteResult deleteByActorId(Long actorId);
}
