package com.gxitsky.lock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gxing
 * @desc TODO
 * @date 2022/1/9
 */
@Configuration
public class RedissionConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
//        config.useSingleServer().setTimeout(5000).setAddress("redis://192.168.0.120:6379");
        config.useSingleServer().setTimeout(5000).setAddress("redis://192.168.50.187:6379");
        return Redisson.create(config);
    }
}
