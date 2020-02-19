package com.api.concurrent.limit.component;

public class LeakyBucketLimiter {

    public static void main(String[] args) throws InterruptedException {
        LeakyBucket leakyBucket = new LeakyBucket();
        for (int i = 0; i < 20; i++) {
            boolean flag = leakyBucket.tryGet();
            System.out.println(flag);
            Thread.sleep(200);
        }
    }


    /**
     * 漏桶
     */
    private static class LeakyBucket {

        //桶中剩余水的数量
        private volatile int water = 0;
        //最近一次漏水时间
        private volatile long lastTime = 0;
        //桶的容量
        private int capacity = 5;
        //流出速率(时间毫秒)
        private float rate = (float) 20 / (10 * 1000);

        public synchronized boolean tryGet() {
            long now = System.currentTimeMillis();
            int leaked = (int) ((now - lastTime) * rate);

            if (leaked > 0) {
                //表示在这期间有流出
                lastTime = now;
            }
            //计算桶中剩余的水
            water = Math.max(0, water - leaked);

            if (water <= capacity) {
                water++;
                return true;
            }else {
                return false;
            }
        }
    }
}
