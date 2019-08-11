package com.springboot.nosql.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

//MongoClient及MongoDbFactory
//配置数据库连接属性
@Configuration
public class MongodbConfig {
    String database = null;

    //注解MongoClient为Bean
    @Bean
    public MongoClient mongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://root:123456@10.0.3.4:27017/admin");
        this.database = mongoClientURI.getDatabase();
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(mongoClient(), database);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }
}
