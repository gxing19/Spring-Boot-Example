package com.gxitsky.service;

import com.gxitsky.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ActorService {

    Actor addActor(Actor actor);

    void deleteByActorId(Long actorId);

    List<Actor> queryAll();

    Actor queryByActorId(Long actorId);

    Long countActor();

    Long countBy(Actor actor);

    List<Actor> queryByFirstName(String firstName);

    int updateFirstName(Long actorId, String firstName);

    List<Actor> queryByLastName(String lastName);

    List<Actor> queryByFirstNameAndLastName(String firstName, String lastName);

    List<Actor> queryByFirstNameWithSortDesc(Sort sort);

    Page<Actor> queryByPage(PageRequest pageRequest);
}
