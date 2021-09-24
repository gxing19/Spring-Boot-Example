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

    /**
     * @desc 发送简单文本邮件
     * @author gxing
     * @date 2021/9/24 10:39
     */
    @RequestMapping("/sendSimpleMail")
    public void sendSimpleMail() {
        emailService.sendSimpleMail();
    }

    /**
     * @desc MimeMessagePreparator消息
     * @author gxing
     * @date 2021/9/24 10:29
     */
    @RequestMapping("/sendWithMimeMessagePreparator")
    public void sendWithMimeMessagePreparator() {
        emailService.sendWithMimeMessagePreparator();
    }

    /**
     * @desc MimeMessageHelper消息
     * @author gxing
     * @date 2021/9/24 10:32
     */
    @RequestMapping("/sendWithMimeMessageHelper")
    public void sendWithMimeMessageHelper() {
        emailService.sendWithMimeMessageHelper();
    }

    /**
     * @desc 发送带附件的邮件
     * @author gxing
     * @date 2021/9/24 10:34
     */
    @RequestMapping("/sendWithAttachments")
    public void sendWithAttachments() {
        emailService.sendWithAttachments();
    }

    /**
     * @desc HTML邮件, 内联静态资源附件
     * helper.setText(content, true);第二个参数设置为true表示html邮件
     * @author gxing
     * @date 2021/9/24 10:36
     */
    @RequestMapping("/sendWithInlineResource")
    public void sendWithInlineResource() {
        emailService.sendWithInlineResource();
    }

    /**
     * @desc 发送模板(Thymeleaf)邮件
     * @author gxing
     * @date 2021/9/24 10:36
     */
    @RequestMapping("/sendTemplateMail")
    public void sendTemplateMail() {
        emailService.sendTemplateMail();
    }

}