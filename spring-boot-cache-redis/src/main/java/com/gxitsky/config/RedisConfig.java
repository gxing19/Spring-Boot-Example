package com.gxitsky.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.net.UnknownHostException;

@Configuration
@EnableRedisRepositories//Spring Boot 默认已开启对Redis Repository的支持，此注解可省略
public class RedisConfig {

    /**
     * SpringBoot已自动注册了这两个Bean
     */
    /*@Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }*/

    /**
     * redis默认使用jdk的二进制数据来序列化
     * 以下自定义使用jackson来序列化
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        /* 序列化10000个对象数据,在Redis 所占用空间
         * 根据最终测试, String 和 FastJson 占用较少,
         * 将10000个对象存在一个HASH列表中,所占空间更少,只需要2.24M
         * */
//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);//2.5M,若开启类型检测是2.96M
//        StringRedisSerializer serializer = new StringRedisSerializer();//2.33M
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);//2.35M
//        KryoRedisSerializer serializer = new KryoRedisSerializer(Object.class);//2.35M
//        MsgpackRedisSerializer serializer = new MsgpackRedisSerializer();//2.96M

//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(om);

        template.setKeySerializer(new StringRedisSerializer()); //1
        template.setValueSerializer(serializer); //2

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    /*@Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);

    }*/

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(":" + method.getName());
                for (Object obj : objects) {
                    sb.append(":" + obj.toString());
                }

                return sb;
            }
        };
    }


}
