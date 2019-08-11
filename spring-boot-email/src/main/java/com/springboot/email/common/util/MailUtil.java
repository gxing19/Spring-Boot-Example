package com.springboot.email.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender; // 自动注入的Bean

    @Value("${spring.mail.username}")
    private String sender; // 读取配置文件中的参数

    /**
     * 发送邮件测试方法
     */
    public void sendTestMail() {
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        mimeMessage.setFrom(sender);
        mimeMessage.setTo(sender);
        mimeMessage.setSubject("邮件发送测试");
        mimeMessage.setText("邮件发送测试内容");
        mailSender.send(mimeMessage);
    }

    /**
     * 发送简易邮件
     *
     * @param mailBean
     */
    public void sendTextMail(MailBean mailBean) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(sender);
            helper.setTo(sender);
            helper.setSubject(mailBean.getSubject());
            helper.setText(mailBean.getText());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件-邮件正文是HTML
     *
     * @param mailBean
     */
    public void sendHtmlMail(MailBean mailBean) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(sender);
            helper.setTo(sender);
            helper.setSubject(mailBean.getSubject());
            helper.setText(mailBean.getText(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件-附件邮件
     *
     * @param mailBean
     */
    public void sendAttachmentMail(MailBean mailBean) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(sender);
            helper.setSubject(mailBean.getSubject());
            helper.setText(mailBean.getText(), true);
            // 增加附件名称和附件
            helper.addAttachment(mailBean.getAttachmentFilename(), mailBean.getFile());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 内联资源（静态资源）邮件发送
     * 由于邮件服务商不同，可能有些邮件并不支持内联资源的展示
     * 在测试过程中，新浪邮件不支持，QQ邮件支持
     * 不支持不意味着邮件发送不成功，而且内联资源在邮箱内无法正确加载
     *
     * @param mailBean
     */
    public void sendInlineMail(MailBean mailBean) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(sender);
            helper.setSubject(mailBean.getSubject());

            /*
             * 内联资源邮件需要确保先设置邮件正文，再设置内联资源相关信息
             */
            helper.setText(mailBean.getText(), true);
            helper.addInline(mailBean.getContentId(), mailBean.getFile());

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模板邮件发送
     *
     * @param mailBean
     */
    public void sendTemplateMail(MailBean mailBean) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(sender);
            helper.setSubject(mailBean.getSubject());
            helper.setText(mailBean.getText(), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
