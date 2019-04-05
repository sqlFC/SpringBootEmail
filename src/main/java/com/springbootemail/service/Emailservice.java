package com.springbootemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
     * 发送邮件
     *
     * @param toUser  发送给谁
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
}
