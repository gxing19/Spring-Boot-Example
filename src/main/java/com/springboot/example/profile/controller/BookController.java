package com.springboot.example.profile.controller;

import com.springboot.example.profile.Author;
import com.springboot.example.profile.MysqlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @class: BootController
 * @desc: 配置文件中的属性注入到代码中
 * @author: gxing
 * @date: 2018年5月1日
 */
@RestController
@EnableConfigurationProperties(MysqlProperties.class)
public class BookController {

	/**
	 * @Value将配置文件属性的值注入到代码中
	 */
	@Value("${book.author}")
	private String bookAuthor;
	@Value("${book.name}")
	private String bookName;
	@Value("${book.price}")
	private double bookPrice;
	
	@Value("${mysql.url}")
	private String mysqlUrl;

	@Autowired
	private Author author;

	@Autowired
	private MysqlProperties mysqlPro;

	@RequestMapping("/index")
	String index() {
		return "bookAuthor=" + bookAuthor + "; bookName=" + bookName + "; bookPrice=" + bookPrice;
	}

	@RequestMapping("/index1")
	String index1() {
		return "hello spring boot 1111111";
	}

	@RequestMapping("/author")
	public String authorInfo(String a) {
		return author.getName() + "; " + author.getAge();
	}

	@RequestMapping("/mysql")
	String getMysqlPro() {
		return mysqlPro.getAccount() + "; " + mysqlPro.getUrl() + "; " + mysqlPro.getPassword();
	}
}
