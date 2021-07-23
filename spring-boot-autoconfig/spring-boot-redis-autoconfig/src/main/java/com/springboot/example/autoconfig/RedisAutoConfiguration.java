package com.springboot.example.autoconfig;

import com.springboot.example.properties.RedisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置配置类
 *
 * @author gxing
 * 1. 配置类需要注册为Bean
 * 2. Properties属性类注册为Bean
 * 3. 在自动配置类中注入属性类实例
 * 4. 在自动配置类中创建方法返回业务类对象，在方法中使用属性类实例调用方法
 */

@Configuration // 1. 当前注册为Bean
@EnableConfigurationProperties(RedisProperties.class) // 2. HelloServiceProperties注册为Bean
@ConditionalOnClass(RedisConfiguration.class) // 3. 指定类，创建对象
@ConditionalOnProperty(prefix = "redis.config", value = "enabled", matchIfMissing = true) // 4. 对条件进行检查
public class RedisAutoConfiguration {

    private RedisProperties RedisProperties;

    public RedisAutoConfiguration(RedisProperties redisProperties) {
        RedisProperties = redisProperties;
    }

    @Bean
    @ConditionalOnMissingBean(RedisConfiguration.class)
    public RedisConfiguration redisConfiguration() {
        RedisConfiguration redisConfiguration = new RedisConfiguration();
        redisConfiguration.setHost(RedisProperties.getHost());
        redisConfiguration.setPort(RedisProperties.getPort());
        redisConfiguration.setUsername(RedisProperties.getUsername());
        redisConfiguration.setPassword(RedisProperties.getPassword());
        return redisConfiguration;
    }
}
