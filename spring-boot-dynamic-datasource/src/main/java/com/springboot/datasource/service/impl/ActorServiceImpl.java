package com.springboot.datasource.service.impl;

import com.springboot.datasource.common.datasource.DataSourceEnum;
import com.springboot.datasource.common.datasource.annotation.DataSourceSelector;
import com.springboot.datasource.entity.Actor;
import com.springboot.datasource.mapper.ActorMapper;
import com.springboot.datasource.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    @Transactional
    public Actor save(Actor actor) {
        actorMapper.save(actor);
        return actor;
    }

    @Override
    @DataSourceSelector(name = DataSourceEnum.MASTER)
    public List<Actor> getActor(Actor actor) {
        return actorMapper.getActor(actor);
    }

    @Override
    @Transactional
    @DataSourceSelector(name = DataSourceEnum.SLAVE)
    public void saveActorList(Boolean errorFlag) {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor("张","飞", new Date()));
        actorList.add(new Actor("关","羽", new Date()));
        actorList.add(new Actor("刘","备", new Date()));
        actorMapper.saveActorList(actorList);

        //抛异常触发事务回滚
        if(null != errorFlag && errorFlag){
            int i = 1/0;
        }

        actorMapper.save(new Actor("曹","操",new Date()));
    }
}
