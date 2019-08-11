package com.springboot.profile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: BookConfig
 * @Description:
 * @User: gxing
 * @Date: 2018-06-04 18:54
 **/
@Configuration
public class BookConfig {

    /**
     * @Value("${xxx}") 全限定属性名注入值
     */
    @Value("${book.author}")
    private String author;
    @Value("${book.name}")
    private String name;
    @Value("${book.price}")
    private double price;
    @Value("A4")//直接注入值
    private String bookPaper;

    public String getAuthor() {
        return author;
    }

    public BookConfig setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getName() {
        return name;
    }

    public BookConfig setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public BookConfig setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getBookPaper() {
        return bookPaper;
    }

    public BookConfig setBookPaper(String bookPaper) {
        this.bookPaper = bookPaper;
        return this;
    }
}