package com.springboot.workflow.repository;

import com.springboot.workflow.entity.TakeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeLeaveRep extends JpaRepository<TakeLeave, Integer> {
}
