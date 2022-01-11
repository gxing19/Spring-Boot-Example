package com.gxitsky.handler;

import com.alibaba.fastjson.JSON;
import com.gxitsky.common.init.SpringBootInitData;
import com.gxitsky.entity.User;

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
