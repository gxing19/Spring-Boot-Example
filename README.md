# Spring-Boot-Example
Spring-Boot 组件系列整合与使用

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
Restful 风格的接口演示项目

## 13. spring-boot-restTemplate
使用 RestTemplate 调用远程的 Rest 接口, 使用 RestTemplate 需要对 Restful 设计风格有个了解，此示例做为客户端; 结合**12**号项目 spring-boot-restful-service 使用，spring-boot-restful-service 做为远程服务端，供 RestTemplate调用。