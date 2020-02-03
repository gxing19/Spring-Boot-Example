package com.springboot.jpa.controller;

import com.springboot.jpa.entity.User;
import com.springboot.jpa.repository.UserRepository;
import com.springboot.jpa.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/add")
    public User addUser() {
        User user = new User().setId(10L).setAddress("中国").setAge(11).setName("Rose");
        return userService.addUser(user);
    }

    @RequestMapping("/queryById")
    public User queryById(Long id) {
        return userService.queyrById(id);
    }

    //-------------示例：跳过Service层直接调用UserRepository----------------------------------

    /**
     * @method: addUser
     * @desc: 使用默认主键(未显式设置主键生成策略)需要人为指定主键值
     * @return: void
     * @author: gxing
     * @date: 2018年5月6日
     */
    @RequestMapping("/addOne")
    public User addOne() {
        User user = new User().setId(1L).setAddress("广州")
                .setAge(31).setName("张小小");
        return userRepository.save(user);
    }

    //查询所有
    @RequestMapping("/queryAll")
    public List<User> queryAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    //条件查询
    @RequestMapping("/queryByNameAndAge")
    public List<User> queryByName(String name, Integer age) {
        Example<User> example = new Example<User>() {

            //查询条件
            public User getProbe() {
                User user = new User()
                        .setName(name)
                        .setAge(age);
                return user;
            }

            //匹配规则
            public ExampleMatcher getMatcher() {
                //条件或
//				ExampleMatcher matcher = ExampleMatcher.matchingAny();
                //条件与,当条件为空时则不匹配
                ExampleMatcher matcher = ExampleMatcher.matchingAll();
                return matcher;
            }
        };
        List<User> userList = userRepository.findAll(example);
        return userList;
    }

    //自定义hql
    @RequestMapping("/queryByNameAndAddress")
    public User queryByNameAndAddress(String name, String address) {
        User user = userRepository.queryByNameAndAddress(name, address);
        return user;
    }

    //排序-倒序
    /*@RequestMapping("/querySortByAge")
    public List<User> querySortByAge() {
        List<User> userList = userRepository.findAll(new Sort(Sort.Direction.DESC, "age"));
        return userList;
    }*/

    //分页
    @RequestMapping("/queryByPage")
    public Page<User> queryByPage() {
        Page<User> userList = userRepository.findAll(PageRequest.of(1, 2));
        return userList;
    }
}
