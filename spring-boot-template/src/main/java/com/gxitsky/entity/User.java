package com.gxitsky.entity;

import com.gxitsky.entity.base.BaseEntity;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @name: User
 * @desc: 用户信息, 继承基类
 * @author: gxing
 * @date: 2018-09-25 11:48
 **/
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8880038494152944645L;

    private String username;
    private String password;
    private String homeAddress;
    @Transient
    private Integer restMoney;
    private Integer state;

    public User() {
    }

    public User(String username) {
        this.username = username;
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

    public Integer getRestMoney() {
        return restMoney;
    }

    public User setRestMoney(Integer restMoney) {
        this.restMoney = restMoney;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public User setState(Integer state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", restMoney='" + restMoney + '\'' +
                ", state=" + state +
                '}';
    }
}