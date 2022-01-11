package com.gxitsky.entity;


import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String userName;
    private String nickName;

    public User() {
    }

    public User(Integer id, String userName, String nickName) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
}
