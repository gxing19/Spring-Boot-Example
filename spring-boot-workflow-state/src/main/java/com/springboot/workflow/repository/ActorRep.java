package com.springboot.workflow.repository;

import com.springboot.workflow.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRep extends JpaRepository<Actor, Integer> {
}
