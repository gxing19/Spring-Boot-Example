package com.springboot.websocket;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ApplicationTests {

    /*String userListStr;

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
            return new RestTemplateBuilder().setConnectTimeout(1000).setReadTimeout(1000);
        }
    }

    @Test
    public void webTestClientDemo() {
        String responseBody = webTestClient.get().uri("/user/queryAll").exchange().expectStatus().isOk()
                .expectBody(String.class).returnResult().getResponseBody();
        System.out.println("--------------webTestClientDemo-------------" + responseBody);
    }

    @Test
    public void testRestTemplateDemo() throws Exception {
        ResponseEntity<String> entity = this.testRestTemplate.getForEntity("/user/queryAll", String.class);
        System.out.println("------------" + entity.getBody().toString());
        System.out.println("------------" + entity.getStatusCode());
        System.out.println("------------" + entity.getStatusCodeValue());

        System.out.println("------------" + JSON.toJSONString(entity));
    }

    @Test
    public void mockMvcDemo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/queryAll")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("error,错误消息", 200, status);
    }*/

}
