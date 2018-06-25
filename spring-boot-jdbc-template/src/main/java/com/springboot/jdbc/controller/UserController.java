package com.springboot.jdbc.controller;

import com.springboot.jdbc.entity.Actor;
import com.springboot.jdbc.entity.User;
import com.springboot.jdbc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public int addUser(){
        User user = new User().setId(3L).setAddress("中国").setAge(11).setName("Rose");
        return userService.addUser(user);
    }

    @RequestMapping("/update")
    public int updateUser(String name, Long id){
        return userService.updateUser(name, id);
    }

    @RequestMapping("/delete")
    public int delete(Long id){
        return userService.deleteUser(id);
    }

    @RequestMapping("/queryCount")
    public int queryCount(){
        return userService.queryCount();
    }

    @RequestMapping("queryCountByLastName")
    public int queryCountByLastName(String lastName){
        return userService.queryCountByLastName(lastName);
    }

    @RequestMapping("queryLastName")
    public String queryLastName(Long actorId){
        return userService.queryLastName(actorId);
    }

    @RequestMapping("/queryByActorId")
    public Actor queryById(Long actorId){
        return userService.queryByActorId(actorId);
    }

    @RequestMapping("/queryActorList")
    public List<Actor> queryActorList(){
        return userService.queryActorList();
    }
}
