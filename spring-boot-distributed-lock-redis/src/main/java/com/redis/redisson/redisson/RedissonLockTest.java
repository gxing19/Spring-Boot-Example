package com.redis.redisson.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author gxing
 * @desc TODO
 * @date 2022/1/8
 */
public class RedissonLockTest {
    static int fixNam = 5;

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setTimeout(1000000).setAddress("redis://192.168.0.120:6379");
        CountDownLatch latch = new CountDownLatch(fixNam);
        RedissonClient redissonClient = Redisson.create(config);
        ExecutorService executor = Executors.newFixedThreadPool(fixNam);
        for (int i = 0; i < fixNam; i++) {
            executor.submit(new TestLock("client-" + i, redissonClient, latch));
        }
        executor.shutdown();
        latch.await();
        System.out.println("所有任务执行完毕");

    }

    static class TestLock implements Runnable {
        private String name;
        RedissonClient redissonClient;
        private CountDownLatch latch;

        public TestLock() {
        }

        public TestLock(String name, RedissonClient redissonClient, CountDownLatch latch) {
            this.name = name;
            this.redissonClient = redissonClient;
            this.latch = latch;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            RLock lock = redissonClient.getLock("TestLock");
            try {
                System.out.println("等待获取锁:" + this.name);
                boolean result = lock.tryLock(300, 300, TimeUnit.MICROSECONDS);
                if (result) {
                    try {
                        System.out.println("获得锁：" + this.name);
                        Thread.sleep(2 * 100);
                        System.out.println("锁使用完毕：" + this.name);
                        latch.countDown();
                    } finally {
                        lock.unlock();
                        System.out.println("释放锁：" + this.name);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
