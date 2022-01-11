package com.gxitsky.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 漏斗限流算法
 *
 * @author dadiyang
 * @date 2018/9/28
 */
public class FunnelRateLimiter {
    private Map<String, Funnel> funnelMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        FunnelRateLimiter limiter = new FunnelRateLimiter();
        int testAccessCount = 50;
        //桶容量
        int capacity = 5;
        //每单个单位时间允许的流量
        int allowQuota = 10;
        //单位时间 秒
        int perSecond = 30;
        int allowCount = 0;
        int denyCount = 0;

        for (int i = 0; i < testAccessCount; i++) {
            boolean isAllow = limiter.isActionAllowed("Tom", "doSomething", capacity, allowQuota, perSecond);
            if (isAllow) {
                allowCount++;
            } else {
                denyCount++;
            }
            System.out.println("访问权限：" + isAllow);
            Thread.sleep(200);
        }

        System.out.println("报告：");
        System.out.println("漏斗容量：" + capacity);
        System.out.println("漏斗流动速率：" + allowQuota + "次/" + perSecond + "秒");

        System.out.println("测试次数=" + testAccessCount);
        System.out.println("允许次数=" + allowCount);
        System.out.println("拒绝次数=" + denyCount);
    }

    /**
     * 根据给定的漏斗参数检查是否允许访问
     *
     * @param username   用户名
     * @param action     操作
     * @param capacity   漏斗容量
     * @param allowQuota 每单个单位时间允许的流量
     * @param perSecond  单位时间（秒）
     * @return 是否允许访问
     */
    public boolean isActionAllowed(String username, String action, int capacity, int allowQuota, int perSecond) {
        String key = "funnel:" + action + ":" + username;
        if (!funnelMap.containsKey(key)) {
            funnelMap.put(key, new Funnel(capacity, allowQuota, perSecond));
        }
        Funnel funnel = funnelMap.get(key);
        return funnel.watering(1);
    }

    /**
     * 漏斗对象
     */
    private static class Funnel {
        //容量
        private int capacity;
        //流出率
        private float rate;
        //剩余配额
        private int leftQuota;
        //上一次流出时间
        private long lastTime;

        public Funnel(int capacity, int allowQuota, int perSecond) {
            //初始化桶容量
            this.capacity = capacity;
            //计算流出速率
            this.rate = (float) allowQuota / (perSecond * 1000);
        }

        /**
         * 漏斗漏水
         *
         * @param quota 流入量
         * @return 是否有足够的水可以流出（是否允许访问）
         */
        public boolean watering(int quota) {
            //计算剩余配额
            this.makeSpace();
            int left = leftQuota - quota;
            if (left >= 0) {
                //还有剩余,则返回 true
                leftQuota = left;
                return true;
            }
            return false;
        }

        /**
         * 根据上次水流动的时间，重新记算剩余空间
         */
        private void makeSpace() {
            long now = System.currentTimeMillis();
            long times = now - lastTime;
            //计算流出数量
            int leaked = (int) (times * rate);
            if (leaked < 1) {
                //没有流出
                return;
            }
            //剩余配额
            leftQuota = leftQuota + leaked;
            // 如果剩余大于容量，则剩余等于容量
            if (leftQuota > capacity) {
                leftQuota = capacity;
            }
            lastTime = now;
        }
    }
}