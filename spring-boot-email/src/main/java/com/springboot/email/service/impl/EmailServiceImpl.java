package com.springboot.email.service.impl;

import com.springboot.email.entity.User;
import com.springboot.email.service.EmailService;
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
 * @name: EmailServiceImpl
 * @desc: 邮件
 * @author: gxing
 * @date: 2018-10-30 11:34
 **/
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * @desc 发送简单文本邮件
     * @author gxing
     * @date 2021/9/24 10:28
     */
    @Override
    public void sendSimpleMail(String email) {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getEmailAddress());
        message.setSubject("账号注册激活确认");
        message.setText("请点击下面链接激活注册的账号："
                + "\n\t" + "http://xxxx.xxxx.com?code=xxxxxx");
        message.setCc("xxxx@qq.com");
        try {
            javaMailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MailException e) {
            logger.error("邮件发送异常:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @desc MimeMessagePreparator消息
     * @author gxing
     * @date 2021/9/24 10:29
     */
    @Override
    public void sendWithMimeMessagePreparator(String email) {
        String to = "xxxx@163.com";
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(from);
                mimeMessage.setSubject("幸运观众");
                mimeMessage.setText("你已被抽中为幸运观众,可获得8888元的奖品大礼包！");
            }
        };
        try {
            javaMailSender.send(preparator);
            logger.info("邮件发送成功！");
        } catch (MailException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * @desc MimeMessageHelper消息
     * @author gxing
     * @date 2021/9/24 10:32
     */
    @Override
    public void sendWithMimeMessageHelper(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(new InternetAddress(from));
            helper.setTo(email);
            helper.setSubject("双色球中奖");
            helper.setText("恭喜你中了双色球一等奖！！");
            javaMailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * @desc 发送带附件的邮件
     * @author gxing
     * @date 2021/9/24 10:34
     */
    @Override
    public void sendWithAttachments(String email) {
        String to = "xxxx@163.com";
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            //开启multipart模式
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String path = EmailServiceImpl.class.getClassLoader().getResource("").getPath();
            File file = new File(path + "/happy.jpg");
            FileSystemResource resource = new FileSystemResource(file);

            helper.setFrom(new InternetAddress(from));
            helper.setTo(to);
            helper.setSubject("设计图");
            helper.setText("设计图见附件");
            helper.addAttachment("happy.jpg", resource);
            javaMailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("邮件发送异常:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @desc HTML邮件, 内联静态资源附件
     * helper.setText(content, true);第二个参数设置为true表示html邮件
     * @author gxing
     * @date 2021/9/24 10:36
     */
    @Override
    public void sendWithInlineResource(String email) {
        User user = new User().setName("Andy").setEmailAddress("xxxx@163.com");
        MimeMessage message = javaMailSender.createMimeMessage();

        String mailContent = "<html><body>" +
                "<h1>Hello World!</h1>" +
                "<img src='cid:happy.jpg'>" +
                "</body></html>";
        try {
            //开启multipart模式
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String path = EmailServiceImpl.class.getClassLoader().getResource("").getPath();
            File file = new File(path + "/happy.jpg");
            FileSystemResource resource = new FileSystemResource(file);

            helper.setFrom(new InternetAddress(from));
            helper.setTo(user.getEmailAddress());
            helper.setSubject("设计图");
            helper.setText("设计图见附件");
            //邮件正文显示附件
            helper.setText(mailContent, true);
            helper.addInline("happy.jpg", resource);
            javaMailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("邮件发送异常:{}", e);
            e.printStackTrace();
        }
    }

    /**
     * @desc 发送模板(Thymeleaf)邮件
     * @author gxing
     * @date 2021/9/24 10:36
     */
    @Override
    public void sendTemplateMail(String email) {
        String name = "gxing";
        String to = "xxxx@163.com";
        try {
            //获取Thymeleaf模板内容(邮件内容)
            Context context = new Context();
            //设置模板需要的参数
            context.setVariable("name", name);
            //执行模板引擎
            String emailContent = templateEngine.process("emailTemplate", context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("淘宝 11.11 活动");
            helper.setText(emailContent, true);

            javaMailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}