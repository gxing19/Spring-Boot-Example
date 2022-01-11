package com.gxitsky.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class GuavaCacheConfig {

    @Bean
    public LoadingCache<String, AtomicLong> loadingCache() {
        LoadingCache<String, AtomicLong> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, AtomicLong>() {
                    @Override
                    public AtomicLong load(String key) throws Exception {
                        return new AtomicLong(0);
                    }
                });
        return loadingCache;
    }
}
