package com.springboot.aop.service.impl;

import com.springboot.aop.entity.Actor;
import com.springboot.aop.mapper.ActorMapper;
import com.springboot.aop.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Override
    public List<Actor> listByPage(Actor actor) {
        List<Actor> actorList = actorMapper.select(actor);
        return actorList;
    }

    @Override
    public List<Actor> getByPage(Actor actor) {
        List<Actor> actorList = actorMapper.getByPage(actor);
        return actorList;
    }

    @Override
    public Actor getByActorId(Long id) {
//        Actor actor = new Actor();
//        actor.setId(actorId);
//        actor = actorMapper.selectByPrimaryKey(actorId);
        Actor actor = actorMapper.getByActorId(id);

        return actor;
    }
}
