package com.api.concurrent.limit.Controller;

import com.api.concurrent.limit.component.RedisLuaLimit;
import com.api.concurrent.limit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/limit")
public class LimitController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 限流的个数
    private static int MAX_LIMIT = 10;
    // 指定的时间内,单位毫秒
    private static long interval = 20 * 1000;
    // 原子类计数器
    private final AtomicInteger atomic = new AtomicInteger(0);
    //起始时间
    private volatile long lastTime = System.currentTimeMillis();

    @RequestMapping("/get")
    public String getMst() {

        long now = System.currentTimeMillis();
        long micSecond = (long) (now - lastTime);

        try {
            if (atomic.incrementAndGet() > MAX_LIMIT && micSecond < interval) {
                System.out.println("!= :" + atomic.get() + " : " + micSecond + ", please try later");
                return "please try later";
            } else if ((now - lastTime) > interval) {
                lastTime = now;
                atomic.set(1);
                Thread.sleep(200);
                User user = new User().setUsername("Tom").setPassword("123456");
                System.out.println(">> :" + atomic.get() + " : " + micSecond);
                return user.toString();
            }
            //模拟执行业务耗时
            Thread.sleep(200);
            User user = new User().setUsername("Tom").setPassword("123456");
            System.out.println("== :" + atomic.get() + " : " + micSecond);
            return user.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            atomic.decrementAndGet();
//            System.out.println(Thread.currentThread().getName() + ":" + atomic.get());
        }
        return null;
    }

    @RedisLuaLimit(limit = 20)
    @RequestMapping("/redisLimit")
    public String redisLimit() throws InterruptedException {
        System.out.println("=======开始处理业务.......");
        Thread.sleep(200);
        return "=======处理业务.........";
    }


}
