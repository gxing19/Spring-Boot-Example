package com.springboot.datasource.service.impl;

import com.springboot.datasource.entity.Actor;
import com.springboot.datasource.mapper.ActorMapper;
import com.springboot.datasource.service.ActorService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Override
    public Actor getById(Long id) {
        Actor actor = actorMapper.getById(id);
        return actor;
    }

    @Override
    public Actor save(Actor actor) {
        actorMapper.save(actor);
        return actor;
    }

    @Override
    public List<Actor> getActor(Actor actor) {
        return actorMapper.getActor(actor);
    }
}
