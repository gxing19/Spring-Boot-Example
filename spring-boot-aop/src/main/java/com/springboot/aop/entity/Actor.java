package com.springboot.aop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.aop.entity.base.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Actor extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3301670278502438921L;

    private Long actorId;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;

    public Actor() {
    }

    public Actor(Long actorId, String firstName, String lastName, LocalDateTime lastUpdate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public Actor setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }
}
