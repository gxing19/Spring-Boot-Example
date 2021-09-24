package com.springboot.email.service;

/**
 * @name: EmailService
 * @desc: 邮件
 * @author: gxing
 * @date: 2018-10-30 11:26
 **/
public interface EmailService {

    /**
     * @desc 发送简单文本邮件
     * @author gxing
     * @date 2021/9/24 10:28
     */
    void sendSimpleMail();

    /**
     * @desc MimeMessagePreparator发送
     * @author gxing
     * @date 2021/9/24 10:31
     */
    void sendWithMimeMessagePreparator();

    void sendWithMimeMessageHelper();

    void sendWithAttachments();

    void sendWithInlineResource();

    void sendTemplateMail();

}
