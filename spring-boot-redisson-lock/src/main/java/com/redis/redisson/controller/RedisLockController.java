package com.redis.redisson.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lock")
public class RedisLockController {

    @Autowired
    private RedissonClient redisson;

    @RequestMapping("/redisson")
    public void redissonLock() {
        RLock lock = redisson.getLock("anyLock");
        lock.lock();

        try {
            //执行业务
        } finally {
            lock.unlock();
        }
    }
}
