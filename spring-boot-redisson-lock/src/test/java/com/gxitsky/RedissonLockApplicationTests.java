package com.gxitsky;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedissonLockApplicationTests {
    private static volatile boolean stock = true;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void contextLoads() {
    }

    /**
     * @desc 分布式锁多线程并发测试(手动创建线程)
     * @author gxing
     * @date 2022/1/9
     */
/*    @Test
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
    }*/

    /**
     * @desc 分布式锁多线程并发测试(线程池)
     * @author gxing
     * @date 2022/1/9
     */
    @Test
    public void lockTest2() throws InterruptedException {
        String key = "apple";
        String LOCK_KEY = "LOCK_KEY:apple";
        int maxNum = 8;
        final CountDownLatch countDownLatch = new CountDownLatch(maxNum);
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < maxNum; i++) {
            executorService.submit(new StockManger(key, LOCK_KEY, countDownLatch));
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println("end takeTime:" + (System.currentTimeMillis() - startTime));
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
        Thread.sleep(100);
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

    /**
     * @author gxing
     * @desc 库存管理
     * @date 2022/1/9
     */
    class StockManger implements Runnable {
        private String skuId;
        private String LOCK_KEY;
        private CountDownLatch countDownLatch;

        public StockManger(String skuId, String LOCK_KEY, CountDownLatch countDownLatch) {
            this.skuId = skuId;
            this.LOCK_KEY = LOCK_KEY;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            RLock rLock = redissonClient.getLock(LOCK_KEY);
            try {
                System.out.println(name + ":等待获取锁");
                boolean lock = rLock.tryLock(200, 100, TimeUnit.MILLISECONDS);
                if (lock) {
                    try {
                        System.out.println(name + ":获得锁,开始处理业务---------------");
                        decrementStock(skuId, 1);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        rLock.unlock();
                        System.out.println(name + ":释放锁---------------");
                    }
                } else {
                    System.out.println(name + ":获取锁失败---------------");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
