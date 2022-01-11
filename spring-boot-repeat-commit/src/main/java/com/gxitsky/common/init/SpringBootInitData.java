package com.gxitsky.common.init;

import com.gxitsky.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringBootInitData implements CommandLineRunner {

    private List<User> userList = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        userList.add(new User(1, "张三", "three"));
        userList.add(new User(2, "李四", "four"));
        userList.add(new User(3, "王五", "five"));
        userList.add(new User(4, "赵六", "six"));

    }

    public List<User> getUserList() {
        return userList;
    }

    public SpringBootInitData setUserList(List<User> userList) {
        this.userList = userList;
        return this;
    }
}
