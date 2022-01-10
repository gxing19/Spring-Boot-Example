package com.springboot.template;

import com.springboot.template.service1.UserServiceOne;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleDataSourceApplicationTests {

    @Autowired
    StringEncryptor encryptor;

    @Autowired
    private UserServiceOne userServiceOne;

    @Test
    public void contextLoads() {

    }
}
