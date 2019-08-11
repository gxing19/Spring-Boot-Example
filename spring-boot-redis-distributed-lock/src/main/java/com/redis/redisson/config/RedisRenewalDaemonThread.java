package com.redis.redisson.config;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisRenewalDaemonThread extends Thread {

    private String lockKey;
    private long expireTime;
    private RedisTemplate redisTemplate;

    public RedisRenewalDaemonThread() {
    }

    public RedisRenewalDaemonThread(RedisTemplate redisTemplate, String lockKey, long expireTime) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.expireTime = expireTime;
    }

    @Override
    public void run() {
        long restExpire = this.expireTime / 3;
        while (true) {
            try {
                long expire = redisTemplate.getExpire(lockKey);
                System.out.println(expire);
                //如果过期时间小于三分之一,则续期
                if (expire < restExpire) {
                    redisTemplate.expire(lockKey, expireTime, TimeUnit.SECONDS);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
