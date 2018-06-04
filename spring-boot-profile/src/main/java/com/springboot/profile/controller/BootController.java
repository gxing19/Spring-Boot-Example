package com.springboot.profile.controller;

import com.springboot.profile.config.BookConfig;
import com.springboot.profile.config.MysqlProperties;
import com.springboot.profile.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private BookConfig bookConfig;

    @Autowired
    private MysqlProperties mysqlProperties;

    @RequestMapping("/index")
    String index() {
        return "bookAuthor=" + bookConfig.getAuthor() +
                "; bookName=" + bookConfig.getName() +
                "; bookPrice=" + bookConfig.getPrice() +
                "; bookPaper=" + bookConfig.getBookPaper();
    }

    @RequestMapping("/user")
    public String userInfo(String a) {
        return userConfig.getName() + "; " + userConfig.getAge();
    }

    @RequestMapping("/mysql")
    String getMysqlPro() {
        return mysqlProperties.getUsername() + "; " + mysqlProperties.getUrl() + "; " + mysqlProperties.getPassword();
    }
}
