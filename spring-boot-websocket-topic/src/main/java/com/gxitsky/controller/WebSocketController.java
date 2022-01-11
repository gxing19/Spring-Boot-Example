package com.gxitsky.controller;

import com.gxitsky.entity.ClientMsg;
import com.gxitsky.entity.RespResult;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name: WebSocketController
 * @Desc: TODO
 * @User: gxing
 * @Date: 2018-08-21 10:45
 **/
@Controller
public class WebSocketController {

    @RequestMapping("/stomp")
    public String stomp() {
        return "stomp";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/getRespMsg")
    public RespResult handle(ClientMsg clientMsg) throws InterruptedException {
        System.out.println("--------------" + clientMsg.getMsg());
        return new RespResult("收到消息：" + clientMsg.getMsg() + "! " + System.currentTimeMillis());
    }
}

