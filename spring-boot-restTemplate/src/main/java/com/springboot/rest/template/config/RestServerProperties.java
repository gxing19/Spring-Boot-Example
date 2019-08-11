package com.springboot.rest.template.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rest.server")
public class RestServerProperties {

    private String url;

    public String getUrl() {
        return url;
    }

    public RestServerProperties setUrl(String url) {
        this.url = url;
        return this;
    }
}
