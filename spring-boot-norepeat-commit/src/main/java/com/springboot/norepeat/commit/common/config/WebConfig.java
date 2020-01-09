package com.springboot.norepeat.commit.common.config;

import com.springboot.norepeat.commit.common.component.NoRepeatInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noRepeatInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public NoRepeatInterceptor noRepeatInterceptor() {
        return new NoRepeatInterceptor(redisTemplate);
    }*/

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(1000)).setReadTimeout(Duration.ofMillis(1000)).build();
    }
}
