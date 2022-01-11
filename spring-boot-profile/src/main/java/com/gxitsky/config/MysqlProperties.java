package com.gxitsky.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 间接注入参数：从加载的properties文件匹配前缀注入属性参数
 */
@Configuration
@ConfigurationProperties(prefix = "mysql")
public class MysqlProperties {

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public MysqlProperties setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MysqlProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MysqlProperties setPassword(String password) {
        this.password = password;
        return this;
    }
}
