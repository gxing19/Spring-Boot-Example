package com.springboot.datasource.mapper;

import com.springboot.datasource.common.datasource.DataSourceEnum;
import com.springboot.datasource.common.datasource.annotation.DataSourceSelector;
import com.springboot.datasource.entity.Actor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMapper {

//    @Select("select * from actor where actor_id = #{id}")
    Actor getById(@Param("id") Long id);

    void save(Actor actor);

    @DataSourceSelector(name = DataSourceEnum.MASTER)
    List<Actor> getActor(Actor actor);
}
