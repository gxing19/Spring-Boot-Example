package com.springboot.email.controller;

import com.springboot.email.service.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: SendMailController
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-30 11:53
 **/

@RestController
@RequestMapping("/mail")
public class SendMailController {

    @Autowired
    private SendEmail sendEmail;

    @RequestMapping("/send1")
    public void sendMail() {
        sendEmail.sendSimpleMail();
    }

    @RequestMapping("/send2")
    public void sendMail2() {
        sendEmail.sendMailUseMimeMessagePreparator();
    }

    @RequestMapping("/send3")
    public void sendMail3() {
        sendEmail.sendMailUseMimeMessageHelper();
    }

    @RequestMapping("/send4")
    public void sendMail4() {
        sendEmail.sendMailWithAttachments();
    }

    @RequestMapping("/send5")
    public void sendMail5() {
        sendEmail.sendMailInlineResource();
    }

    @RequestMapping("/send6")
    public void sendMail6() {
        sendEmail.sendMailTemplate();
    }

}