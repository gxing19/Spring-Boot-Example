package com.springboot.email.service;

import com.springboot.email.entity.Order;

import javax.mail.MessagingException;

/**
 * @name: OrderManager
 * @desc: 订单管理
 * @author: gxing
 * @date: 2018-10-30 11:26
 **/
public interface SendEmail {

    void sendSimpleMail() ;

    void sendMailUseMimeMessagePreparator();

    void sendMailUseMimeMessageHelper();

    void sendMailWithAttachments();

    void sendMailInlineResource();

    void sendMailTemplate();

}
