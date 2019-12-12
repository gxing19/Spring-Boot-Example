# Spring-Boot-Example
Spring Boot 功能特性和组件系列的整合, 详解与使用。下面所有Demo都是基于 Spring Boot 2.0.x, 2.1.x, 2.2.x 版本。

更多可参考 [Spring Boot 2 实践系列文章](http://www.gxitsky.com/categories/Spring-Boot-2%E5%AE%9E%E8%B7%B5%E7%B3%BB%E5%88%97/), [Spring Cloud 系列文章](http://www.gxitsky.com/categories/Spring-Cloud%E7%B3%BB%E5%88%97/) 。

本人技术博客: http://www.gxitsky.com/ ，欢迎交流。

# 数据库
本示例及所有子项目使用的数据库系统是MySQL, 连接的数据库是 MySQL 官方提供的测试库**sakila**,该测试库可以自行从 GitHub上下载：https://github.com/datacharmer/test_db

## 1. spring-boot-data-rest
[Spring Boot 2实践系列(五)： Spring Data Rest 集成详解和使用](http://www.gxitsky.com/2018/05/25/springboot-app-05-data-rest/)

## 2. spring-boot-data-jpa
[Spring Boot 2实践系列(四)： Spring Data Jpa 集成详解和使用](http://www.gxitsky.com/2018/05/25/springboot-app-04-data-jpa/)

## 3. spring-boot-cache-simple
不使用第三方缓存技术，ConcurrentMapCacheManager，默认使用 ConcurrentHashMap作为存储缓存

[Spring Boot 2实践系列(十)：Spring 缓存体系](http://www.gxitsky.com/2018/05/31/springboot-app-10-spring-cache/)

## 4. spring-boot-cache-ehcache2
添加 ehcache2 依赖和 ehcache.xml配置文件

[Spring Boot实践系列(十一)：Ehcache集成和使用](http://www.gxitsky.com/2018/06/05/springboot-app-11-cache-ehcache/)

## 5. spring-boot-profile
参数注入，前缀匹配注入，加载 properties文件，加载XML文件，从加载的文件中读取参数并注入到属性

## 6. spring-boot-hello-autoconfig
spring boot 自动配置简单示例

## 7. spring-boot-cache-redis
导redis包，设置参数，修改序列化方式;
1. 手动写入缓存,会启用json数据格式序列化
2. 通过注解写入缓存,二进制序列化,配置的json序列化不起效。

[Spring Boot实践系列(十二)：Redis集成和使用 ](http://www.gxitsky.com/2018/06/05/springboot-app-12-redis/)

## 8. spring-boot-nosql-mongodb
集成 mongodb,分别使用 MongoTemplate 和 MongoRepository来执行CRUD的操作

[Spring Boot实践系列(十三)：MongoDB集成与使用 ](http://www.gxitsky.com/2018/06/07/springboot-app-13-mongodb/)

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

## 17. spring-boot-spring-security
集成 Spring Security, 实现认证和授权操作, 项目中数据访问使用JPA, 用到用户表(user), user.sql 文件在项目 document 目录里。

[Spring Boot实践系列(三十)：Spring Security 详解和集成使用 ](http://www.gxitsky.com/2018/08/31/springboot-app-30-spring-security/)

## 18. spring-boot-spring-batch
集成 Spring Batch 示例，使用批处理操作将CSV中的数据处理后导入到数据库中。

[Spring Boot实践系列(三十一)：Spring Batch 批处理框架详解和集成使用 ](http://www.gxitsky.com/2018/09/06/springboot-app-31-spring-batch/)

## 19. spring-boot-password-encrypt
Spring Boot 对配置文件中的密码进行加密,使用两种方案：jasypt 和 druid 自带的非对称加密。jasypt：可以对环境文件中的任意属性值进行加密解密, druid只对数据源的连接密码进行解密。

[Spring Boot 2实践系列(三十二)：Spring Boot 配置文件密码加密两种方案](http://www.gxitsky.com/2018/09/19/springboot-app-32-password-encryptor/)

## 20. spring-boot-template
基于`Spring Boot 2.0.4 Release` 版本的快速开发模板, 尽量采用 `boot-starter` 依赖, 使用最精简的配置。

此模板集成了 `MySQL, Druid, Mybatis, Common Mapper, pageHelper, Log4j2, Shiro, JSTL`, Web页面支持 `JSP`, 支持 Maven 打 `war` 包, 支持打 `Docker` 镜像。

## 21. spring-boot-jms-activemq
集成 ActiveMQ, 消息中间件来实现系统间的异步消息。异步消息通信方式主要有两种：点对点的队列(queue)方式 和 发布订阅的主题(topic)方式。

[Spring Boot 2实践系列(三十四)：集成 AcitveMQ 消息中间件](http://www.gxitsky.com/2018/10/17/springboot-app-34-activemq/)

[Spring Boot 2实践系列(三十三)：JMS 和 AMQP 消息服务及支持的消息组件](http://www.gxitsky.com/2018/10/16/springboot-app-33-spring-jms-mq/)

## 22. spring-boot-jms-rabbitmq
Spring AMQP 默认支持的实现是 Rabbitmq。该项目集成 Rabbitmq, 消息中间件来实现系统间的异步消息。

[Spring Boot 2实践系列(三十五)：集成 RabbitMQ 消息中间件](http://www.gxitsky.com/2018/10/27/springboot-app-35-rabbitmq/)

## 23. spring-boot-email
集成 JavaMail 发送电子邮件：简单文本邮件、带附件邮件、HTML邮件、内嵌静态资源文件、模板邮件。

若使用此示例，请修改发送者的邮箱地址和密码，修改接收者的邮箱地址。

[Spring Boot 2实践系列(三十六)：集成 JavaMail 发送邮件 ](http://www.gxitsky.com/2018/10/29/springboot-app-36-email/)

## 24. spring-boot-redis-distributed-lock
分布式架构之分布式锁 及 Redis 实现分布式锁方案

[分布式微服务应用系列(十一)：分布式锁理解及实现方案](http://www.gxitsky.com/2019/08/03/distributed-micro-app-11-lock/)

## 25. spring-boot-aop
集成拦截器 Interceptor：实现登录验证拦截，API 接口请求限制(防刷)，统一获以 API 接口处理时长。

集成Spring AOP ：实现 API 接口防刷, 统一记录 API 入参和返回数据到日志，统一获以 API 接口处理时长。

[Spring Boot 2实践系列(四十六)：Spring AOP与拦截器实现API接口请求限制](http://www.gxitsky.com/2019/11/22/springboot-app-46-aop-api-request-limit/)

[Spring Boot 2实践系列(四十七)：Spring AOP 实现API接口处理请求耗时监控](http://www.gxitsky.com/2019/11/30/springboot-app-47-aop-api-resp-time-watch/)

[Spring Boot 2实践系列(四十八)：Spring AOP详解与应用](http://www.gxitsky.com/2019/12/01/springboot-app-48-aop-spring-aop/)

[Spring Boot 2实践系列(四十九)：Spring AOP 实现方法入参和返回统一记录日志](http://www.gxitsky.com/2019/12/08/springboot-app-49-aop-log/)

## 26. spring-boot-datasource
Spring AOP 实现数据源动态切换(读写分离)，支持注解显式指定数据源和根据方法名前缀来选择数据源

[Spring Boot 2实践系列(五十)：Spring AOP 实现动态数据源切换](http://www.gxitsky.com/2019/12/11/springboot-app-50-aop-datasource-switch/)
