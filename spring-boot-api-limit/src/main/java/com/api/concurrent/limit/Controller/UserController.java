package com.api.concurrent.limit.Controller;

import com.api.concurrent.limit.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {
    //同一时间只允许3个并发
    private final Semaphore semaphore = new Semaphore(3);

    private final AtomicInteger atomic = new AtomicInteger(0);
    private final static int MAX_LIMIT = 5;

    @RequestMapping("/get")
    public User getUser() throws InterruptedException {
        semaphore.acquire();
        try {
            //模拟执行业务耗时
            Thread.sleep(200);
            User user = new User().setUsername("Tom").setPassword("123456");
            System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
            return user;
        } finally {
            semaphore.release();
        }
    }

    @RequestMapping("/query")
    public String queryUser() {
        try {
            if (atomic.incrementAndGet() > MAX_LIMIT) {
                return "please try later";
            }
            //模拟执行业务耗时
            Thread.sleep(200);
            User user = new User().setUsername("Tom").setPassword("123456");
            System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
            return user.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            atomic.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + ":" + atomic.get());
        }
        return null;
    }
}
