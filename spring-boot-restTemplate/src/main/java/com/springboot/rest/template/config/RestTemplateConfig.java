package com.springboot.rest.template.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate customRestTemplate(){
        //设置超时时间,毫秒
       return new RestTemplateBuilder().setConnectTimeout(5000).setReadTimeout(5000).build();
    }
}
