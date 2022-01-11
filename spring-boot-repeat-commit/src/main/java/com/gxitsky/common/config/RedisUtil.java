package com.gxitsky.common.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static RedisTemplate redisTemplate;

    public RedisUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static boolean setnxAndExpire(final String key, String value, long milliseconds) {

        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, milliseconds, TimeUnit.MILLISECONDS);
        if(null != result && result){
            return result;
        }
        return false;

    }
}
