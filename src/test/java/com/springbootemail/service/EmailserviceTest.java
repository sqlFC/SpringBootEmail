package com.springbootemail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author zhu
 * @date 2019/4/4 21:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailserviceTest {

    @Resource
    public Emailservice emailservice;

    @Test
    public void sendSimpleMail() {
        emailservice.sendSimpleMail("1661009855@qq.com ","spring boot","你好我发送的邮件");
    }
}