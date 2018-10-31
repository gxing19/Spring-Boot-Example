package com.springboot.email.service.impl;

import com.springboot.email.entity.User;
import com.springboot.email.service.SendEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @name: SimpleOrderManager
 * @desc: 发送邮件
 * @author: gxing
 * @date: 2018-10-30 11:34
 **/
@Service
public class SendEmailImpl implements SendEmail {

    private static final Logger logger = LogManager.getLogger(SendEmailImpl.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单文本邮件
     */
    @Override
    public void sendSimpleMail() {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getEmailAddress());
        message.setSubject("账号注册激活确认");
        message.setText("请点击下面链接激活注册的账号："
                + "\n\t" + "http://xxxx.xxxx.com?code=xxxxxx");
        message.setCc("xxxx@qq.com");
        try {
            mailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MailException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * MimeMessagePreparator发送
     */
    @Override
    public void sendMailUseMimeMessagePreparator() {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getEmailAddress()));
                mimeMessage.setFrom(from);
                mimeMessage.setSubject("幸运观众");
                mimeMessage.setText("你已被抽中为幸运观众,可获得8888元的奖品大礼包！");
            }
        };
        try {
            mailSender.send(preparator);
            logger.info("邮件发送成功！");
        } catch (MailException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * MimeMessageHelper发送
     */
    @Override
    public void sendMailUseMimeMessageHelper() {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(new InternetAddress(from));
            helper.setTo(user.getEmailAddress());
            helper.setSubject("双色球中奖");
            helper.setText("恭喜你中了双色球一等奖！！");
            mailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * 发送带附件的邮件
     */
    @Override
    public void sendMailWithAttachments() {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //开启multipart模式
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String path = SendEmailImpl.class.getClassLoader().getResource("").getPath();
            File file = new File(path + "/happy.jpg");
            FileSystemResource resource = new FileSystemResource(file);

            helper.setFrom(new InternetAddress(from));
            helper.setTo(user.getEmailAddress());
            helper.setSubject("设计图");
            helper.setText("设计图见附件");
            helper.addAttachment("happy.jpg", resource);
            mailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * HTML邮件,内联静态资源附件
     * helper.setText(content, true);第二个参数设置为true表示html邮件
     */
    @Override
    public void sendMailInlineResource() {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");
        MimeMessage message = mailSender.createMimeMessage();

        String mailContent = "<html><body>" +
                "<h1>Hello World!</h1>" +
                "<img src='cid:happy.jpg'>" +
                "</body></html>";
        try {
            //开启multipart模式
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String path = SendEmailImpl.class.getClassLoader().getResource("").getPath();
            File file = new File(path + "/happy.jpg");
            FileSystemResource resource = new FileSystemResource(file);

            helper.setFrom(new InternetAddress(from));
            helper.setTo(user.getEmailAddress());
            helper.setSubject("设计图");
            helper.setText("设计图见附件");
            //邮件正文显示附件
            helper.setText(mailContent, true);
            helper.addInline("happy.jpg", resource);
            mailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * 发送模板(Thymeleaf)邮件
     */
    @Override
    public void sendMailTemplate() {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");
        try {
            //获取Thymeleaf模板内容(邮件内容)
            Context context = new Context();
            //设置模板需要的参数
            context.setVariable("name", user.getName());
            //执行模板引擎
            String emailContent = templateEngine.process("emailTemplate", context);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(user.getEmailAddress());
            helper.setSubject("淘宝 11.11 活动");
            helper.setText(emailContent,true);

            mailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}