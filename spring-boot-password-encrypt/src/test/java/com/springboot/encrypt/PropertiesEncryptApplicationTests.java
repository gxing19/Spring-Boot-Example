package com.springboot.encrypt;

import com.alibaba.druid.filter.config.ConfigTools;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesEncryptApplicationTests {

    @Autowired
    StringEncryptor encryptor;


    @Test
    public void jacketEncrypt() {

        //加密
        String name = encryptor.encrypt("admin");
        String password = encryptor.encrypt("123456");
        System.out.println("name 密文: " + name);
        System.out.println("password 密文: " + password);

        //解密
        String decrypt1 = encryptor.decrypt(name);
        String decrypt2 = encryptor.decrypt(password);
        System.out.println(decrypt1 + "------------" + decrypt2);
    }


    @Test
    public void druidEncrypt() throws Exception {
        //密码明文
        String password = "123456";
        System.out.println("明文密码: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];

        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);

        System.out.println("privateKey:" + privateKey);
        System.out.println();
        System.out.println("publicKey:" + publicKey);

        System.out.println("password:" + password);

        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密后:" + decryptPassword);
    }
}
