package com.gxitsky.component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc 令牌桐
 * @author gxing
 * @date 2022/1/9
 */
public class TokenBucketLimiter {

    public static void main(String[] args) throws InterruptedException {
        TokenBucket tokenBucket = new TokenBucket();
        for (int i = 0; i < 20; i++) {
            boolean flag = tokenBucket.tryAcquire();

            System.out.println(flag);
            Thread.sleep(150);
        }
    }

    public static class TokenBucket {
        private Lock lock = new ReentrantLock();
        //当前桶中令牌个数
        private volatile long token = 0;
        //上次添加令牌的时间
        private volatile long lastTime = 0;
        //桶容量
        private int capacity = 6;
        //令牌放入桶中的速率 5/s
        private int rate = 5;

        public TokenBucket() {
        }

        public TokenBucket(int capacity, int rate) {
            this.capacity = capacity;
            this.rate = rate;
        }

        public boolean tryAcquire() {
            lock.lock();
            try {
                long now = System.currentTimeMillis();
                long between = now - lastTime;
                //两次请求期间放入桶中的令牌数
                long inTokens = ((now - lastTime) / 1000) * rate;
                if (inTokens > 0) {
                    lastTime = now;
                }

                //计算桶中的令牌,与桶容量比较,取较小值
                token = token + inTokens;
                token = Math.min(token, capacity);

                if (token > 0) {
                    token--;
                    return true;
                }
            } finally {
                lock.unlock();
            }
            return false;
        }
    }


}
