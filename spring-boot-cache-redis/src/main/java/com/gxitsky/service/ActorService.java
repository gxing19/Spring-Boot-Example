package com.gxitsky.service;

import com.gxitsky.entity.Actor;

public interface ActorService {

    Actor queryById(Long actorId);

    Actor queryByActorId(Long actorId);

    void saveActorToString();

    void saveActorToHash();

    void saveToHashTest();
}
