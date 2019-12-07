package com.springboot.aop.mapper;

import com.springboot.aop.entity.Actor;
import com.springboot.aop.mapper.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMapper extends BaseMapper<Actor> {

    Actor getByActorId(Long actorId);

    List<Actor> getByPage(Actor actor);
}
