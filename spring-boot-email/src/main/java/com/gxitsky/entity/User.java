package com.gxitsky.entity;

import java.io.Serializable;

/**
 * @name: User
 * @desc: TODO
 **/
public class User implements Serializable {

    private String name;
    private Integer age;
    private String emailAddress;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
}