package com.springboot.repeatcommit.handler;

import com.alibaba.fastjson.JSON;
import com.springboot.repeatcommit.common.init.SpringBootInitData;
import com.springboot.repeatcommit.entity.User;

import java.util.List;

public class VerifyHandler {

    private List<User> userList;
    private SpringBootInitData bootInitData;

    public VerifyHandler(SpringBootInitData bootInitData) {
        this.bootInitData = bootInitData;
        this.userList = bootInitData.getUserList();
    }

    public void printUser() {

        System.out.println(JSON.toJSONString(userList));
    }
}
