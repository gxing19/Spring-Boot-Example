package com.springboot.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 自动注册属性类
 * 用于读取application.properties中的参数
 *
 * @author gxing
 */

@ConfigurationProperties(prefix = "redis.config") //1. 参数绑定，指定前辍
public class RedisProperties {

    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
