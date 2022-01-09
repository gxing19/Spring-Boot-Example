package com.api.concurrent.limit.component;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @desc 平滑限流
 * @author gxing
 * @date 2022/1/9
 */
public class SmoothWarmingUpLimiter {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 5; i++) {
            System.out.println(limiter.acquire());
        }

        Thread.sleep(1000L);
        for (int i = 0; i < 5; i++) {
            System.out.println(limiter.acquire());
        }

    }
}
