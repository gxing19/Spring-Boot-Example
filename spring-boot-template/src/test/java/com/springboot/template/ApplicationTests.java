package com.springboot.template;

import com.springboot.template.common.constant.ParamConst;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void contextLoads() {

    }

    @Test
    public void encryPwd(){
        String encryPwd = new Md5Hash("123456",ParamConst.PWD_SALT,2).toString();
        System.out.println(encryPwd);
    }

}
