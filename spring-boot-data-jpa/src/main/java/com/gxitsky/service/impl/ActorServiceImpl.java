package com.gxitsky.service.impl;

import com.gxitsky.repository.ActorRepository;
import com.gxitsky.entity.Actor;
import com.gxitsky.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    /**
     * 增
     *
     * @param actor
     * @return
     */
    @Override
    public Actor addActor(Actor actor) {
//        return actorRepository.save(actor);
        return actorRepository.saveAndFlush(actor);
    }

    /**
     * 删: deleteById()
     *
     * @param actorId
     * @return
     */
    @Override
    public void deleteByActorId(Long actorId) {
        actorRepository.deleteById(actorId);
    }

    /**
     * 改
     *
     * @param firstName
     * @return
     */
    @Override
    public int updateFirstName(Long actorId, String firstName) {
        return actorRepository.updateFirstName(actorId, firstName);
    }

    /**
     * 查: 所有, findAll()
     *
     * @return
     */
    @Override
    public List<Actor> queryAll() {
        return actorRepository.findAll();
    }

    /**
     * 查: 条件-主键, findById(primary key)
     *
     * @param actorId
     * @return
     */
    @Override
    public Actor queryByActorId(Long actorId) {
        //springboot 1.5.3.Release
//        return actorRepository.findOne(actorId);

        //springboot 2.0.0.Release及以上
        return actorRepository.findById(actorId).get();
    }

    /**
     * 查: 条件-非主键字段
     * 非主键查询都是匹配对象属性查询
     *
     * @param firstName
     * @return
     */
    @Override
    public List<Actor> queryByFirstName(String firstName) {
        Example<Actor> example = Example.of(new Actor().setFirstName(firstName));
        return actorRepository.findAll(example);
    }

    /**
     * 查: 定制查询条件和匹配规则
     *
     * @param firstName
     * @param lastName
     * @return
     */
    @Override
    public List<Actor> queryByFirstNameAndLastName(String firstName, String lastName) {

        Actor actor = new Actor().setFirstName(firstName).setLastName(lastName);

        Example<Actor> example = new Example<Actor>() {
            //构造参与查询的参数
            @Override
            public Actor getProbe() {
                return actor;
            }

            //设置查询匹配规则
            @Override
            public ExampleMatcher getMatcher() {
                return ExampleMatcher.matchingAny();
            }
        };
//        return actorRepository.findAll(example);

        return actorRepository.findAll(Example.of(actor, ExampleMatcher.matchingAll()));
    }

    /**
     * 排序查询
     *
     * @param sort
     * @return
     */
    @Override
    public List<Actor> queryByFirstNameWithSortDesc(Sort sort) {
        return actorRepository.findAll(sort);
    }

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    @Override
    public Page<Actor> queryByPage(PageRequest pageRequest) {
        return actorRepository.findAll(pageRequest);
    }

    /**
     * 使用HQL查询语句
     *
     * @param lastName
     * @return
     */
    @Override
    public List<Actor> queryByLastName(String lastName) {
        return actorRepository.queryByLastName(lastName);
    }

    /**
     * 统计: 所有, count()
     *
     * @return
     */
    @Override
    public Long countActor() {
        return actorRepository.count();
    }

    /**
     * 统计: 条件,全匹配实体类属性,count(example)
     *
     * @param actor
     * @return
     */
    @Override
    public Long countBy(Actor actor) {
//        Actor actor = new Actor().setFirstName(firstName);
//        Example<Actor> example = Example.of(actor);
        return actorRepository.count(Example.of(actor));
    }
}
