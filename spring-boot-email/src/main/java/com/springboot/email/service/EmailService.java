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
     * @desc MimeMessagePreparator消息
     * @author gxing
     * @date 2021/9/24 10:29
     */
    void sendWithMimeMessagePreparator();

    /**
     * @desc MimeMessageHelper消息
     * @author gxing
     * @date 2021/9/24 10:32
     */
    void sendWithMimeMessageHelper();

    /**
     * @desc 发送带附件的邮件
     * @author gxing
     * @date 2021/9/24 10:34
     */
    void sendWithAttachments();

    /**
     * @desc HTML邮件, 内联静态资源附件
     * helper.setText(content, true);第二个参数设置为true表示html邮件
     * @author gxing
     * @date 2021/9/24 10:36
     */
    void sendWithInlineResource();

    /**
     * @desc 发送模板(Thymeleaf)邮件
     * @author gxing
     * @date 2021/9/24 10:36
     */
    void sendTemplateMail();

}
