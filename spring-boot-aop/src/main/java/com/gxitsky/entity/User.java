package com.gxitsky.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -4024267176811951208L;

    private Long id;
    private String username;
    private String password;
    private String address;
    private Integer state;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参转换
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")//出参
    private LocalDate birthday;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadDateTime;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
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

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public User setState(Integer state) {
        this.state = state;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public User setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public LocalDateTime getDeadDateTime() {
        return deadDateTime;
    }

    public User setDeadDateTime(LocalDateTime deadDateTime) {
        this.deadDateTime = deadDateTime;
        return this;
    }
}
