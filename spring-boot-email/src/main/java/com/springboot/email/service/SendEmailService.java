package com.springboot.email.service;

/**
 * @name: SendEmailService
 * @desc: 发送邮件
 * @author: gxing
 * @date: 2018-10-30 11:26
 **/
public interface SendEmailService {

    /**
     * 发送简单邮件
     */
    void sendSimpleMail();

    void sendMailUseMimeMessagePreparator();

    void sendMailUseMimeMessageHelper();

    void sendMailWithAttachments();

    void sendMailInlineResource();

    void sendMailTemplate();

}
