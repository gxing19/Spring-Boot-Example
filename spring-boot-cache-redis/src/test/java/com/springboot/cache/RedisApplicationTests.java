package com.springboot.cache;

import com.springboot.cache.service.ActorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private ActorService actorService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void saveActorToRedis(){
        actorService.saveActorToString();

    }

    @Test
    public void saveActorToHash(){
        actorService.saveActorToHash();

    }

    @Test
    public void saveToHashTest(){
        actorService.saveToHashTest();
    }

}
