package com.springboot.websecurity.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * @Name: Demo1
 * @Desc: TODO
 * @User: gxing
 * @Date: 2018-09-04 16:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1 {

    @Test
    public void bcryptPassword() {
        String password = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        System.out.println("{bcrypt}" + encode);
    }
}