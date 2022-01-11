package com.gxitsky.Controller;

import com.gxitsky.component.ApiLimit;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/limit")
public class CacheLimiterController {


    /*private LoadingCache<String, AtomicLong> loadingCache;

    @PostConstruct
    public void init() {
        LoadingCache<String, AtomicLong> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, AtomicLong>() {
                    @Override
                    public AtomicLong load(String key) throws Exception {
                        return new AtomicLong(0);
                    }
                });
        this.loadingCache = loadingCache;
    }*/

    @Autowired
    private LoadingCache<String, AtomicLong> loadingCache;

    /**
     * 2秒限制20次请求
     *
     * @throws ExecutionException
     */
    @RequestMapping("/cache1")
    public String cacheLimit1() throws ExecutionException, InterruptedException {
        long limit = 20;
        long currentSeconds = System.currentTimeMillis() / 1000;
        Thread.sleep(300);
        if (loadingCache.get(String.valueOf(currentSeconds)).incrementAndGet() > limit) {
            System.out.println("xxxxxxx限流了........." + "Second:" + currentSeconds + ", " + loadingCache.get(String.valueOf(currentSeconds)));
            return "xxxxxxx限流了.........";
        }
        System.out.println("=======处理业务......." + "Second:" + currentSeconds + ", " + loadingCache.get(String.valueOf(currentSeconds)));
        return "=======处理业务.........";

    }

    @ApiLimit(times = 20)
    @RequestMapping("/cache2")
    public String cacheLimit2() throws InterruptedException {
        System.out.println("=======开始处理业务.......");
        Thread.sleep(200);
        return "=======处理业务.........";

    }

}
