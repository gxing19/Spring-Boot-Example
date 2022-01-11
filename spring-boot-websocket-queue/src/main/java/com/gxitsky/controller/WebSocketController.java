package com.gxitsky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @Name: WebSocketController
 * @Desc: 消息控制器
 * @User: gxing
 * @Date: 2018-08-21 10:45
 **/
@Controller
public class WebSocketController {

    //注入简单消息模板给客户端,用于发送消息,默认有个目标前缀/user,可自定义
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        //下面的代码就是如果发送人是Michael，接收人就是Janet，发送的信息是message，反之亦然。
        if (principal.getName().equals("tom")) {
            //通过SimpMessagingTemplate的convertAndSendToUser向用户发送消息。
            //第一参数表示接收信息的用户，第二个是浏览器订阅的地址，第三个是消息本身
            simpMessagingTemplate.convertAndSendToUser("kitty", "/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        } else {
            simpMessagingTemplate.convertAndSendToUser("tom", "/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        }
    }
}

