package com.springboot.jpa.repository;

import com.springboot.jpa.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    /**
     * @param actorId
     * @param firstName
     * @return
     * @Query注解的SQL语句是HQL,是对对象的查询 更新和删除操作必需开启事务, 否则会报:
     * nested exception is javax.persistence.TransactionRequiredException
     */
    @Modifying
    @Transactional(timeout = 1000)
    @Query("update Actor set firstName = ?2 where actorId = ?1")
    int updateFirstName(Long actorId, String firstName);

    /**
     * @param lastName
     * @return
     * @Query注解传参有两种方式 1. 第一种是按参数的序号传参。
     * 2. 第二种是绑定参数名传参,方法参数必须使用@Param注解来与HQL入参绑定
     */
//    @Query(value = "select a from Actor a where a.lastName = ?1")
    @Query(value = "select a from Actor a where a.lastName = :lastName")
    List<Actor> queryByLastName(@Param("lastName") String lastName);

}

