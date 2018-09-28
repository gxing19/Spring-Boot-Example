package com.springboot.template.entity;

import com.springboot.template.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * @name: User
 * @desc: 用户信息,继承基类
 * @author: gxing
 * @date: 2018-09-25 11:48
 **/
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8880038494152944645L;

    private String username;
    private String password;
    private String homeAddress;
    private Integer state;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String homeAddress) {
        this.username = username;
        this.password = password;
        this.homeAddress = homeAddress;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public User setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public User setState(Integer state) {
        this.state = state;
        return this;
    }
}