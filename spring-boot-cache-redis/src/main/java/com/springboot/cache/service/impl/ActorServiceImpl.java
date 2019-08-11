package com.springboot.cache.service.impl;

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
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Optional;

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
     *
     * @param actorId
     * @return
     */
    @Override
    public Actor queryById(Long actorId) {
        Actor actor = (Actor) actorRedisDao.getActor(String.valueOf(actorId));
//        return actor != null ? actor : actorRepository.findById(actorId).get();
        if (actor == null) {
            System.out.println("------redis 缓存不存在 actorId = " + actorId + " 的数据--------");
            actor = actorRepository.findById(actorId).get();
            actorRedisDao.save(String.valueOf(actor.getActorId()), actor);
        } else {
            System.out.println("--------actorId = " + actorId + " 的数据来自于redis缓存---------");
        }
        return actor;
    }

    /**
     * 使用Cache注解
     * 先查缓存,没有再查库,再写入缓存
     * 缓存注解使用:http://112.74.59.39/2018/06/01/spring-cache-annotation/
     *
     * @param actorId
     * @return
     */
    @Override
    @Cacheable(key = "#actorId", value = "actor")
    public Actor queryByActorId(Long actorId) {
        Actor actor = actorRepository.findById(actorId).get();
        System.out.println("--------数据来自于数据库---------");
        return actor;
    }

    @Override
    public void saveActorToString() {
        Actor actor = new Actor();
        for (Long i = 0l; i < 10000; i++) {
            actor.setActorId(i).setFirstName("first_" + i)
                    .setLastName("last_" + i).setLastUpdate(new Date());
            redisTemplate.opsForValue().set(String.valueOf(actor.getActorId()), actor);
        }
    }

    @Override
    public void saveActorToHash() {
        Actor actor = new Actor();
        for (Long i = 0l; i < 10000; i++) {
            actor.setActorId(i).setFirstName("first_" + i)
                    .setLastName("last_" + i).setLastUpdate(new Date());
            /*redisTemplate.opsForHash().put("actor" + i, "lastName",actor.getLastName());
            redisTemplate.opsForHash().put("actor" + i, "firstName",actor.getFirstName());
            redisTemplate.opsForHash().put("actor" + i, "lastUpdate",actor.getLastUpdate());*/

            //每个KEY 存1000个字段; 10000条数据占内存 1.77M
            Long a = i / 1000;
            redisTemplate.opsForHash().put("actor:" + a, String.valueOf(i), actor);
        }
    }

    @Override
    public void saveToHashTest() {
        String nickName1 = null;
        String nickName2 = null;
        String nickName3 = null;
        int profile_id = 0;

        for (int i = 0; i < 50000; i++) {
            nickName1 = "Tom";
            nickName2 = "Kitty";
            nickName3 = "Andy";

            nickName1 = nickName1 + i;
            nickName2 = nickName2 + i;
            nickName3 = nickName3 + i;
            profile_id = i;

            String digest = DigestUtils.md5DigestAsHex((nickName1 + nickName2 + nickName3).getBytes());
            String key = digest.substring(0, 2);

            redisTemplate.opsForHash().put(key, nickName1, String.valueOf(profile_id));
            redisTemplate.opsForHash().put(key, nickName2, String.valueOf(profile_id));
            redisTemplate.opsForHash().put(key, nickName3, String.valueOf(profile_id));

            /*
            设置：hash-max-zipmap-entries 1000
            5万条数据占内存3.90M
            取三个呢称的md5的前两位，就有256个key(16*16)
            将每个呢称作为key 的 field，profile_id作为 value
            */
        }
    }

    @Cacheable(value = "#userId", keyGenerator = "keyGenerator")
    public Actor getById(Long userId) {
        Actor actor = new Actor();
        Optional<Actor> actorOptional = actorRepository.findById(userId);
        return actorOptional.get();
    }
}
