package com.redis.redisson;

import com.redis.redisson.config.RedisDistributedLock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockApplicationTests {

    String lockKey = "lock_key_apple";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redisson;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisDistributedLock redisDistributedLock;


    @Test
    public void contextLoads() {
    }

    @Test
    public void testToLock() throws InterruptedException {
        String lockKey = "goods_id_10002";
        String lockValue = String.valueOf(System.currentTimeMillis());
        try {
            boolean lockResult = redisDistributedLock.tryToLock(lockKey, lockValue, 6);
            System.out.println("lock result: " + lockResult);
            Thread.sleep(10 * 1000);

        } finally {
            boolean releaseResult = redisDistributedLock.releaseLockByLua(lockKey, lockValue);
            System.out.println("release result: " + releaseResult);
        }
    }

    @Test
    public void releaseLock() throws InterruptedException {
        long threadId = Thread.currentThread().getId();
        boolean lockResult = redisDistributedLock.tryToLock("goods_id_10001", String.valueOf(threadId), 10);
        System.out.println(lockResult);
    }


    @Test
    public void testAdd100Goods() {
        stringRedisTemplate.opsForValue().set("apple", "100");
        Assert.assertEquals("100", stringRedisTemplate.opsForValue().get("apple"));
    }

    @Test
    public void testDistributedLock() {

        for (int i = 0; i < 55; i++) {
            RLock lock = redisson.getLock(lockKey);
            lock.lock(60, TimeUnit.SECONDS);
            try {
                int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("apple"));
                if (stock > 0) {
                    stringRedisTemplate.opsForValue().set("apple", String.valueOf(stock - 1));
                    System.out.println("old_stock:" + stock + "; " + "stock-1:" + (stock - 1));
                }
            } finally {
                lock.unlock();
            }
        }

    }

}
