package com.springboot.jpa.repository;

import com.springboot.jpa.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @query注解方式: 是对对象的操作,SQL语句是对象和对像属性
 */

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    /**
     * @Query注解
     * 更新和删除操作必需开启事务,否则会报:nested exception is javax.persistence.TransactionRequiredException
     * @param actorId
     * @param firstName
     * @return
     */
    @Modifying
    @Transactional(timeout = 1000)
    @Query("update Actor set firstName = ?2 where actorId = ?1")
    int updateFirstName(Long actorId, String firstName);
}

