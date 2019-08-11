package com.springboot.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    //    @Column(name = "realName")        //字段名映射
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdate;

    public Long getActorId() {
        return actorId;
    }

    public Actor setActorId(Long actorId) {
        this.actorId = actorId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Actor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Actor setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Actor setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }
}
