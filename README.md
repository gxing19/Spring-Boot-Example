# Spring-Boot-Example
Spring Boot 功能特性和组件系列的整合, 详解与使用。下面所有Demo都是基于 Spring Boot 2.0.x版本。

# 数据库
本示例及所有子项目使用的数据库系统是MySQL, 连接的数据库是 MySQL 官方提供的测试库**sakila**,该测试库可以自行从 GitHub上下载：https://github.com/datacharmer/test_db

## 1. spring-boot-data-rest
文章：http://112.74.59.39/2018/05/25/springboot-app-5-data-rest/

## 2. spring-boot-data-jpa
文章：http://112.74.59.39/2018/05/25/springboot-app-4-data-jpa/

## 3. spring-boot-cache-simple
不使用第三方缓存技术，ConcurrentMapCacheManager，默认使用 ConcurrentHashMap作为存储缓存

文章：http://112.74.59.39/2018/05/31/springboot-app-10-cache/

## 4. spring-boot-cache-ehcache2
添加 ehcache2 依赖和 ehcache.xml配置文件

## 5. spring-boot-profile
参数注入，前缀匹配注入，加载 properties文件，加载XML文件，从加载的文件中读取参数并注入到属性

## 6. spring-boot-hello-autoconfig
spring boot 自动配置简单示例

## 7. spring-boot-cache-redis
导redis包，设置参数，修改序列化方式;
1. 手动写入缓存,会启用json数据格式序列化
2. 通过注解写入缓存,二进制序列化,配置的json序列化不起效。

## 8. spring-boot-nosql-mongodb
集成 mongodb,分别使用 MongoTemplate 和 MongoRepository来执行CRUD的操作

## 9. spring-boot-admin
集成 spring-boot-admin 服务器和客户端，监控管理应用

## 10. spring-boot-test
演示Spring Boot 提供的测试组件：testRestTemplate，webTestClient，MockMvc

## 11. spring-boot-JdbcTemplate
JdbcTemplate 操作数据库简单示例

## 12. spring-boot-restful-service
Restful 风格的接口演示项目(严格来说这仍不是一个标准的Rest服务,Dao层使用的是JPA,一个标准的Rest服务在新增资源成功后应返回该资源的URI)

## 13. spring-boot-restTemplate
使用 RestTemplate 调用远程的 Rest 服务接口, 使用 RestTemplate 需要对 Restful 设计风格有个了解。
此项目需要结合 **12** 号 spring-boot-restful-service 项目一起启动，spring-boot-restTemplate 此项目做为客户端, ，spring-boot-restful-service 做为远程服务端，供 RestTemplate调用。

## 14. spring-boot-web
Spring Boot Web相关配置，静态资源，SSL等，主要涉及Spring MVC相关的内容。

## 15. spring-boot-websocket-topic
Spring Boot WebSocket Topic, 广播式通信(订阅式)。

## 16. spring-boot-websocket-queue
Spring Boot WebSocket Queue, 点对点通信。
此项目集成了 Spring Security 用作登录验证来区分用户, Web使用的是Thymeleaf模板。
想改造成 JSP 和静态 HTML 都不行, 改造后出现点击登录重新回到登录界面页面(/login),不会跳转到/chat路径，待严究清楚Spring Security再细究。
