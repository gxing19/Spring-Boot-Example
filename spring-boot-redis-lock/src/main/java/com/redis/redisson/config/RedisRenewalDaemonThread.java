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
        long renew  = this.expireTime / 3;
        //如果过期时间很短，小于3秒
        if (renew == 0) {
            renew = 1L;
        }
        System.out.println("rest expire: " + renew);
        while (true) {
            try {
                long expire = redisTemplate.getExpire(lockKey);
                System.out.println(expire);
                //如果剩余过期时间小于等于三分之一,则续期。
                //注意，这里是小于等于，若只是小于，expire 可能出现负数
                if (expire <= renew) {
                    redisTemplate.expire(lockKey, expireTime, TimeUnit.SECONDS);
                }
                //请求间隔，降低无效请求频率
                Thread.sleep(renew * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
