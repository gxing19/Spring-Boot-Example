package com.gxitsky.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: TopicController
 * @desc: Topic
 * @author: gxing
 * @date: 2019-05-08 14:47
 **/
@RestController
@RequestMapping("/kafka")
public class SendController {

    private static final Logger logger = LogManager.getLogger(SendController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/topic/{msg}")
    public void sendMsg(@PathVariable String msg) {
        ListenableFuture result = kafkaTemplate.send("NBA", msg);
        result.addCallback(o -> System.out.println("send msg success"),
                throwable -> System.out.println("send msg fail"));
    }
}