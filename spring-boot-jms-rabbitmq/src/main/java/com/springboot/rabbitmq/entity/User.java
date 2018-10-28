package com.springboot.rabbitmq.entity;

import java.io.Serializable;

/**
 * @name: User
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-25 18:42
 **/
public class User implements Serializable {

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}