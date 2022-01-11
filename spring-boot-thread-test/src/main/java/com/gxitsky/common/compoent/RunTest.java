package com.gxitsky.common.compoent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RunTest implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(RunTest.class);

    @Autowired
    private RestTemplate restTemplate;

    private int maxThread = 22;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("执行多线程测试");
        String url = "http://localhost:8080/limit/redisLimit";
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);

        /*for(int i=0; i<10; i++){
            String userId = "userId" + i;
            HttpEntity request = buildRequest(userId);
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
//                    System.out.println("Thread:"+Thread.currentThread().getName()+", time:"+System.currentTimeMillis());
                    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
                    System.out.println("Thread:"+Thread.currentThread().getName() + "," + response.getBody());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }*/
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < maxThread; i++) {
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
//                    ResponseEntity<User> responseEntity = restTemplate.getForEntity(url, User.class);
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                    System.out.println(Thread.currentThread().getName() + ":" + responseEntity.getBody());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.countDown();
        System.out.println("总耗时:" + (System.currentTimeMillis() - startTime));
    }

    private HttpEntity buildRequest(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("NO_REPEAT_TOKEN", "a25a4876d19c40c59aa7c83117cb6fbb");
        Map<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        return new HttpEntity<>(body, headers);
    }
}
