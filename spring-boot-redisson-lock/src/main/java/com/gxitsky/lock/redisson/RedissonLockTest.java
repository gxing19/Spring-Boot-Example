package com.gxitsky.lock.redisson;

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
    static int fixNum = 5;

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setTimeout(1000000).setAddress("redis://192.168.0.120:6379");
        RedissonClient redissonClient = Redisson.create(config);

        CountDownLatch latch = new CountDownLatch(fixNum);
        ExecutorService executor = Executors.newFixedThreadPool(fixNum);
        for (int i = 0; i < fixNum; i++) {
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

        //https://www.cnblogs.com/qdhxhz/p/11046905.html
        //https://www.cnblogs.com/cjsblog/p/9831423.html
        //https://www.jianshu.com/p/f302aa345ca8
        //https://blog.csdn.net/loushuiyifan/article/details/82497455
        @Override
        public void run() {
            RLock lock = redissonClient.getLock("TestLock");
            try {
                System.out.println("等待获取锁:" + this.name);
                boolean isLock = lock.tryLock(300, 300, TimeUnit.MICROSECONDS);
                if (isLock) {
                    try {
//                        lock.lock();
                        System.out.println("获得锁：" + this.name);
                        Thread.sleep(200);
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
