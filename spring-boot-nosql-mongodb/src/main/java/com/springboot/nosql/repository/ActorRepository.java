package com.springboot.nosql.repository;

import com.springboot.nosql.entity.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends MongoRepository<Actor, Long> {
}
