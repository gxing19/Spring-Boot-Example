package com.gxitsky.mapper;

import com.gxitsky.entity.Actor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMapper {

//    @Select("select * from actor where actor_id = #{id}")
    Actor getById(@Param("id") Long id);

    void save(Actor actor);

    List<Actor> getActor(Actor actor);

    void saveActorList(List<Actor> actorList);
}
