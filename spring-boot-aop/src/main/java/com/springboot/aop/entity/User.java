package com.springboot.aop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.aop.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4024267176811951208L;

    private Long id;
    private String username;
    /**
     * 性别：1-男,0-女
     */
    private Integer sex;
    private String password;
    private String address;
    @Transient
    private String phone;
    private Integer state;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参转换
    @Transient
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")//出参
    private LocalDate birthday;
    @Transient
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

    public Integer getSex() {
        return sex;
    }

    public User setSex(Integer sex) {
        this.sex = sex;
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

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
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
