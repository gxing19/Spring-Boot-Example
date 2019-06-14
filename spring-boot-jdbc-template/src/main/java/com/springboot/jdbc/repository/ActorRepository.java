package com.springboot.jdbc.repository;

import com.springboot.jdbc.entity.Actor;
import org.springframework.data.repository.CrudRepository;

/**
 * @name: ActorRepository
 * @desc: TODO
 * @author: gxing
 * @date: 2019-06-13 10:50
 **/
public interface ActorRepository extends CrudRepository<Actor, Long> {
}
