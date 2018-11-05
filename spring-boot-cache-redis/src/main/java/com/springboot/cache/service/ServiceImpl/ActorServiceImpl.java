package com.springboot.cache.service.ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.springboot.cache.dao.ActorRedisDao;
import com.springboot.cache.entity.Actor;
import com.springboot.cache.repository.ActorRepository;
import com.springboot.cache.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActorServiceImpl implements ActorService {

    private static final Logger logger = LogManager.getLogger(ActorServiceImpl.class);


    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorRedisDao actorRedisDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 先查Redis缓存,没有从库里查再写入缓存,手动写入Redis库
     * @param actorId
     * @return
     */
    public Actor queryById(Long actorId) {
        Actor actor = (Actor) actorRedisDao.getActor(String.valueOf(actorId));
//        return actor != null ? actor : actorRepository.findById(actorId).get();
        if(actor == null){
            System.out.println("------redis 缓存不存在 actorId = " + actorId + " 的数据--------");
            actor = actorRepository.findById(actorId).get();
            actorRedisDao.save(String.valueOf(actor.getActorId()), actor);
        }else {
            System.out.println("--------actorId = " + actorId + " 的数据来自于redis缓存---------");
        }
        return actor;
    }

    /**
     * 使用Cache注解
     * 先查缓存,没有再查库,再写入缓存
     * 缓存注解使用:http://112.74.59.39/2018/06/01/spring-cache-annotation/
     * @param actorId
     * @return
     */
    @Override
    @Cacheable(key = "#actorId",value = "actor")
    public Actor queryByActorId(Long actorId) {
        Actor actor = actorRepository.findById(actorId).get();
        System.out.println("--------数据来自于数据库---------");
        return actor;
    }

    public void saveActorToRedis(){
        Actor actor = new Actor();
        for (Long i = 0l; i < 10000; i++) {
            actor.setActorId(i).setFirstName("first_" + i)
                    .setLastName("last_" + i).setLastUpdate(new Date());
            redisTemplate.opsForValue().set(String.valueOf(actor.getActorId()),actor);
        }
    }
}
