package com.springboot.rest.template.service.impl;

import com.alibaba.fastjson.JSON;
import com.springboot.rest.template.entity.Actor;
import com.springboot.rest.template.service.RestTemplateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private static final Logger logger = LogManager.getLogger(RestTemplateServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;


    //------------------get方式----------------------------------

    /**
     * 获取数据-所有
     * getForEntity()
     * 包含响应头,响应体数据
     */
    /*@Override
    public List<Actor> queryAll() {
        String uri = "http://localhost:8080/actor/queryActorList";
        ResponseEntity<Object> actorResponseEntity = null;
        try {
            actorResponseEntity = restTemplate.getForEntity(uri,Object.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(actorResponseEntity));
        return (List<Actor>) actorResponseEntity.getBody();
    }*/

    /**
     * getForObject()
     * 返回的只有实体数据,没有响应头的数据
     */
    /*@Override
    public List<Actor> queryAll() {
        String uri = "http://localhost:8080/actor/queryActorList";
        Object object = restTemplate.getForObject(uri, Object.class);
        return JSON.parseArray(JSON.toJSONString(object),Actor.class);
    }*/

    @Override
    public List<Actor> queryAll() {
        String uri = "http://localhost:8080/actor/queryActorList";
        return new ArrayList<>();
    }

    /**
     * 获取数据-参数
     * getForEntity()
     *
     * @param actorId
     * @return
     */
    @Override
    public Actor queryByActorId(Long actorId) {
        String uri = "http://localhost:8080/actor/queryByActorId?actorId=" + actorId;
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);

        //Body是LinkHashMap类型
        /*Object body = responseEntity.getBody();
        String str = JSON.toJSONString(body);
        Actor actor = JSON.parseObject(str, Actor.class);*/

        Actor actor = JSON.parseObject(JSON.toJSONString(responseEntity.getBody()), Actor.class);

        return actor;
    }















/*    @Override
    public String getUserById(Long id) {
        String object = restTemplate.getForObject("http://localhost:8080/user/queryByActorId", String.class, 1L);
        return object;
    }*/

    @Override
    public int addActor(Actor actor) throws URISyntaxException {
        String uri = "http://localhost:8080/actor/add";

        RequestCallback requestCallback = new RequestCallback() {
            @Override
            public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
                clientHttpRequest.execute();
            }
        };

        ResponseExtractor actorResponseEntity = new ResponseExtractor() {
            @Override
            public Object extractData(ClientHttpResponse clientHttpResponse) throws IOException {
                return clientHttpResponse;
            }
        };

        Object execute = restTemplate.execute(uri, HttpMethod.GET, requestCallback, actorResponseEntity);

/*        String str = "http://localhost:8080/actor/add";
//        ?actorId=3001&firstName=Robin&lastName=Zhang
        URI uri = new URI(str);
        HttpEntity<Actor> actorHttpEntity = new RequestEntity<Actor>(HttpMethod.GET,uri);
        ResponseEntity<Actor> actorResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, actorHttpEntity, Actor.class);*/

        System.out.println(JSON.toJSONString(execute));

        return 1;
    }

    @Override
    public int queryCount() {
        return restTemplate.getForObject("http://localhost:8080/user/queryCount", Integer.class);
    }


}
