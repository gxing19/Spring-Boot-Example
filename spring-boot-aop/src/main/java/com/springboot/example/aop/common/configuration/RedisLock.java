package com.springboot.example.aop.common.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class RedisLock {

    public boolean tryLock(String key, Object value, Long time) {
        boolean result = this.tryLock(key, value, TimeUnit.MILLISECONDS, time);
        return result;
    }

    public boolean tryLock(String key, Object value, TimeUnit timeUnit, Long time) {

        return true;
    }

    public boolean releaseLock(String key, Object value) {

        return true;
    }
}
