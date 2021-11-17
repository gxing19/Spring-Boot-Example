package com.springboot.web;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class SpringSecurityApplicationTests {

    String userListStr;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private WebTestClient webTestClient;

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @TestConfiguration
    static class Config {

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(10000)).setReadTimeout(Duration.ofSeconds(10000));
        }
    }

    /**
     * webTestClientDemo
     */
    @Test
    public void webTestClientDemo() {
        String responseBody = webTestClient.get().uri("/user/queryAll").exchange().expectStatus().isOk()
                .expectBody(String.class).returnResult().getResponseBody();
        System.out.println("--------------webTestClientDemo-------------" + responseBody);
    }


    /**
     * TestRestTemplate
     *
     * @throws Exception
     */
    @Test
    public void testRestTemplateDemo() throws Exception {
        ResponseEntity<String> entity = this.testRestTemplate.getForEntity("/user/queryAll", String.class);
        System.out.println("------------" + entity.getBody().toString());
        System.out.println("------------" + entity.getStatusCode());
        System.out.println("------------" + entity.getStatusCodeValue());

        System.out.println("------------" + JSON.toJSONString(entity));
    }

    /**
     * MockMvc
     *
     * @throws Exception
     */
    @Test
    public void mockMvcDemo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/queryAll")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("error,错误消息", 200, status);
    }

}
