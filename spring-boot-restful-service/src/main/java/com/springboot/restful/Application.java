package com.springboot.restful;

import com.alibaba.fastjson.JSON;
import com.springboot.restful.common.config.StartConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("args:", JSON.toJSONString(args));
        new StartConfig(args);
        SpringApplication.run(Application.class, args);
    }
}
