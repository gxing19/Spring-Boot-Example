package com.redis.redisson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisDistributedLock {

    private static final Boolean LOCK_SUCCESS = true;
    private static final Long RELEASE_SUCCESS = 1L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 加锁
     *
     * @param lockKey
     * @param lockValue  通常为 threadId 或 requestId
     * @param expireTime
     * @return 是否加锁成功
     */
    public boolean tryToLock(String lockKey, String lockValue, int expireTime) {
        //set(key,value,expire_time,nx)，如果不存在则设置,成功返回true,表示加锁成功
        Boolean lockResult = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, expireTime, TimeUnit.SECONDS);
        boolean result = LOCK_SUCCESS.equals(lockResult);
        if (result) {
            RedisRenewalDaemonThread renewalDaemonThread = new RedisRenewalDaemonThread(redisTemplate, lockKey, expireTime);
            renewalDaemonThread.setDaemon(true);
            renewalDaemonThread.start();
        }
        return result;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param lockValue 通常为 threadId 或 requestId
     * @return 是否释放成功
     */
    public boolean releaseLockByLua(String lockKey, String lockValue) {

        //释放锁 lua 脚本,
        String luaScript = "if (redis.call('get', KEYS[1]) == ARGV[1]) then return redis.call('del', KEYS[1]) else return 0 end";
        //注意要设置脚本执行返回类型
        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        List<String> keyList = new ArrayList<>();
        keyList.add(lockKey);
        Long releaseResult = redisTemplate.execute(redisScript, keyList, lockValue);
        return RELEASE_SUCCESS.equals(releaseResult);

    }

    /**
     * 释放锁(问题:判断锁和释放锁是两个操作,是非原子性的)
     *
     * @param lockKey
     * @param lockValue 通常为 threadId 或 requestId
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String lockValue) {

        String value = (String) redisTemplate.opsForValue().get(lockKey);
        if (lockValue.equals(value)) {
            return redisTemplate.delete(lockKey);
        }
        return false;
    }
}
