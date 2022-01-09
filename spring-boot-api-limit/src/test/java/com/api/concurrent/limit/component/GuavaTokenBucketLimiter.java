package com.api.concurrent.limit.component;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.CountDownLatch;

/**
 * @desc Guava令牌桐限流
 * @author gxing
 * @date 2022/1/9
 */
public class GuavaTokenBucketLimiter {

    public static void main(String[] args) throws InterruptedException {
        //每秒新增 10 个令牌, 即每隔 100ms 新增一个令牌
        final RateLimiter rateLimiter = RateLimiter.create(10);

//        Thread.sleep(10000);

        int maxNum = 50;

        final CountDownLatch countDownLatch = new CountDownLatch(maxNum);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < maxNum; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //获取(消费)一个令牌
                    rateLimiter.acquire();
                    System.out.println("RUN_TASK:" + Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            });
            thread.setName("Name_" + i);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("end takeTime" + (System.currentTimeMillis() - startTime));

        /*CountDownLatch countDownLatch = new CountDownLatch(50);
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        long startTime = System.currentTimeMillis();
        for(int i=0; i<maxNum; i++){
            executorService.submit(() -> {
                try {
                    rateLimiter.acquire();
                    System.out.println("Thread:"+Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println("end takeTime:" + (System.currentTimeMillis() - startTime));*/
    }
}
