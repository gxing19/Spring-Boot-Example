package com.springboot.email.controller;

import com.springboot.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: SendMailController
 * @desc: 邮件
 * @author: gxing
 * @date: 2018-10-30 11:53
 **/

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/sendSimpleMail")
    public void sendSimpleMail() {
        emailService.sendSimpleMail();
    }

    @RequestMapping("/send2")
    public void sendMail2() {
        emailService.sendMailUseMimeMessagePreparator();
    }

    @RequestMapping("/send3")
    public void sendMail3() {
        emailService.sendWithMimeMessageHelper();
    }

    @RequestMapping("/send4")
    public void sendMail4() {
        emailService.sendWithAttachments();
    }

    @RequestMapping("/send5")
    public void sendMail5() {
        emailService.sendWithInlineResource();
    }

    @RequestMapping("/send6")
    public void sendMail6() {
        emailService.sendTemplateMail();
    }

}