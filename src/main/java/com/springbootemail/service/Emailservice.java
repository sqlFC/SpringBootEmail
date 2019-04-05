package com.springbootemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author zhu
 * @date 2019/4/4 21:27
 */

@Service
public class Emailservice {

    @Value("${spring.mail.username}")
    public String fromUserName;

    @Autowired
    public JavaMailSender javaMailSender;

    /**
     * 发送文本邮件
     *
     * @param toUser  发送地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String toUser, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toUser);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(fromUserName);
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送html邮件
     *
     * @param toUser  发送地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendHtmlmail(String toUser, String subject, String content) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage);
        mimeMessageHelper.setTo(toUser);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);
        mimeMessageHelper.setFrom(fromUserName);
        javaMailSender.send(mailMessage);
    }

    /**
     * 发送带附件邮件
     * @param toUser 发送地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param filePath 附件地址
     * @throws MessagingException
     */
    public void sendInlinResourceMail(String toUser,
                                      String subject,
                                      String content,
                                      String filePath) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage,true);
        mimeMessageHelper.setTo(toUser);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);
        mimeMessageHelper.setFrom(fromUserName);
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        mimeMessageHelper.addAttachment(fileName,file);
        javaMailSender.send(mailMessage);
    }
}
