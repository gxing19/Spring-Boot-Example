package com.springboot.cache.dao;

import com.springboot.cache.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @ClassName: ActorRepositoryImpl
 * @Description:
 * @User: gxing
 * @Date: 2018-06-06 10:23
 **/
@Repository
public class ActorRedisDao{

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> objValueOperations;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> stringValueOperations;

    public void stringRedisTemplateDmoe(){
        stringValueOperations.set("name","kitty");
    }

    public void save(String key, Object obj){
        objValueOperations.set(key,obj);
    }

    public String getStr(){
        return stringValueOperations.get("name");
    }

    public Object getActor(String key){
       return objValueOperations.get(key);
    }
}