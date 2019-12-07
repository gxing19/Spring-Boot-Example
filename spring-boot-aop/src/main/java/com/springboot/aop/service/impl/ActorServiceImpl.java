package com.springboot.aop.service.impl;

import com.springboot.aop.entity.Actor;
import com.springboot.aop.mapper.ActorMapper;
import com.springboot.aop.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Override
    public List<Actor> listActor() {
        List<Actor> actorList1 = actorMapper.selectAll();
        List<Actor> actorList2 = actorMapper.getActorByPage();
        return actorList2;
    }

    @Override
    public Actor getByActorId(Long actorId) {
//        Actor actor = new Actor();
//        actor.setId(actorId);
//        actor = actorMapper.selectByPrimaryKey(actorId);
        Actor actor = actorMapper.getByActorId(actorId);

        return actor;
    }
}
