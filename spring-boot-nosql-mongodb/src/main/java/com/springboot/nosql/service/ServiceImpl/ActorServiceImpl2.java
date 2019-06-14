package com.springboot.nosql.service.ServiceImpl;

import com.springboot.nosql.entity.Actor;
import com.springboot.nosql.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @name: ActorServiceImpl2
 * @desc: TODO
 * @author: gxing
 * @date: 2019-06-13 09:27
 **/
@Service
public class ActorServiceImpl2 {

    @Autowired
    private ActorRepository actorRepository;

    /**
     * 保存
     * @param actor
     * @return
     */
    public Actor addActor(Actor actor ){
        actor.setLastUpdate(new Date());
        return actorRepository.save(actor);
    }

    /**
     * 查所有
     * @return
     */
    public List<Actor> findAll( ){
        return actorRepository.findAll();
    }

    /**
     * 根据ID查
     * @param actorId
     * @return
     */
    public Actor findById(Long actorId){
        return actorRepository.findById(actorId).get();
    }

    /**
     * 根据ID删除
     * @param actorId
     */
    public void deleteById(Long actorId){
        actorRepository.deleteById(actorId);
    }

    //MongoRepository没有update方法
}