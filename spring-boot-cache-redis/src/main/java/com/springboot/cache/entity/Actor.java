package com.springboot.cache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Actor implements Serializable {

    private static final long serialVersionUID = -336743741742403986L;

    @Id
    private Long actorId;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
