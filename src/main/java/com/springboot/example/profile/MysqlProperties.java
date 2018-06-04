package com.springboot.example.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 前缀属性注入
 */
@ConfigurationProperties(prefix="mysql")
public class MysqlProperties {
	
	private String url;
	private String account;
	private String password;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
