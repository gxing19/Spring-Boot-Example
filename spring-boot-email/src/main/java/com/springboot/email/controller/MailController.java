package com.springboot.email.controller;

import com.springboot.email.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: SendMailController
 * @desc: 发生邮件
 * @author: gxing
 * @date: 2018-10-30 11:53
 **/
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private SendEmailService sendEmailService;

    /**
     * 发送简单文本邮件
     */
    @RequestMapping("/sendSimpleMail")
    public void sendSimpleMail() {
        sendEmailService.sendSimpleMail();
    }

    /**
     * 发送
     */
    @RequestMapping("/send2")
    public void sendMail2() {
        sendEmailService.sendMailUseMimeMessagePreparator();
    }

    @RequestMapping("/send3")
    public void sendMail3() {
        sendEmailService.sendMailUseMimeMessageHelper();
    }

    @RequestMapping("/send4")
    public void sendMail4() {
        sendEmailService.sendMailWithAttachments();
    }

    @RequestMapping("/send5")
    public void sendMail5() {
        sendEmailService.sendMailInlineResource();
    }

    @RequestMapping("/send6")
    public void sendMail6() {
        sendEmailService.sendMailTemplate();
    }

}