package com.gxitsky.lock;

import com.gxitsky.lock.config.RedisLockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisLockApplicationTests {

    private static volatile boolean stock = true;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisLockUtil redisLockUtil;


    @Test
    public void contextLoads() {
    }

    /**
     * @desc 分布式锁多线程并发测试(手动创建线程)
     * @author gxing
     * @date 2022/1/9
     */
    @Test
    public void lockTest1() throws InterruptedException {
        String key = "apple";
        String LOCK_KEY = "LOCK_KEY:apple";
        int maxNum = 8;
        final CountDownLatch countDownLatch = new CountDownLatch(maxNum);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < maxNum; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tryLock(LOCK_KEY);
                        decrementStock(key, 1);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setName("Thread_Name_" + i);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("end takeTime:" + (System.currentTimeMillis() - startTime));
    }

    /**
     * @desc 分布式锁多线程并发测试(线程池)
     * @author gxing
     * @date 2022/1/9
     */
    @Test
    public void lockTest2() throws InterruptedException {
        String key = "apple";
        String LOCK_KEY = "LOCK_KEY:apple";
        int maxNum = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(maxNum);
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < maxNum; i++) {
            executorService.submit(() -> {
                Thread thread = Thread.currentThread();
                try {
                    tryLock(LOCK_KEY);
                    decrementStock(key, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    redisLockUtil.unlock(LOCK_KEY, String.valueOf(thread.getId()));
                    System.out.println(thread.getName() + ":释放锁---------------");
                    Thread.currentThread().interrupt();
                }
                countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println("end takeTime:" + (System.currentTimeMillis() - startTime));
    }

    /**
     * @param lockKey 锁的Key
     * @desc 获取锁
     * @author gxing
     * @date 2022/1/9
     */
    public boolean tryLock(String lockKey) throws InterruptedException {
        Thread thread = Thread.currentThread();
        boolean lock = redisLockUtil.tryLock(lockKey, String.valueOf(thread.getId()), 300, TimeUnit.MILLISECONDS);
        if (!lock) {
            System.out.println(thread.getName() + ":获取锁失败");
            Thread.sleep(300);
            tryLock(lockKey);
        } else {
            System.out.println(thread.getName() + ":获得锁,开始处理业务---------------");
        }
        return lock;
    }

    /**
     * @param skuId 商品 skuId
     * @desc 减库存业务
     * @author gxing
     * @date 2022/1/9
     */
    public void decrementStock(String skuId, long num) throws InterruptedException {
        String name = Thread.currentThread().getName();
        if (!stock) {
            System.out.println(name + ":秒杀已结束");
            return;
        }
        Thread.sleep(400);
        String count = redisTemplate.opsForValue().get(skuId);
        assert count != null;
        if (Integer.parseInt(count) > 0) {
            Long decrement = redisTemplate.opsForValue().decrement(skuId, num);
            System.out.println(name + ":执行完减库存,剩余数量:" + decrement + "---------------");
        } else {
            stock = false;
            System.out.println(name + ":库存不足---------------");
            throw new RuntimeException(name + ":库存不足");
        }
    }
}
