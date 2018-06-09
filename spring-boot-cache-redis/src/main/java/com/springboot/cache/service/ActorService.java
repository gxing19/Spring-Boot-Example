package com.springboot.cache.service;

import com.springboot.cache.entity.Actor;

public interface ActorService {

    Actor queryById(Long actorId);

    Actor queryByActorId(Long actorId);
}
