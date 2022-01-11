package com.gxitsky.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Name: SysUser
 * @Desc: 实现UserDetails接口, 该用户实体类即为Spring Security 所使用的用户
 * @User: gxing
 * @Date: 2018-09-04 17:18
 **/
@Entity
@Table(name = "user")
//public class SysUser{
public class SysUser implements UserDetails {           //实现 UserDetails,重写里面方法

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String role;
    private String address;
    private Integer age;
    private Boolean state;

    public Long getId() {
        return id;
    }

    public SysUser setId(Long id) {
        this.id = id;
        return this;
    }

    public SysUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public SysUser setRole(String role) {
        this.role = role;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SysUser setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public SysUser setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public SysUser setState(Boolean state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 重写getAuthorities方法,将用户角色做为权限
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //添加授权
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(getRole()));
        return authorityList;
    }
}