package com.springboot.rest.template.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.rest.template.config.RestServerProperties;
import com.springboot.rest.template.entity.City;
import com.springboot.rest.template.service.CallRemoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class CallRemoteServiceImpl implements CallRemoteService {

    private static final Logger logger = LogManager.getLogger(CallRemoteServiceImpl.class);

    @Autowired
    private RestServerProperties restServerProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * getForEntity()
     * 获取数据-所有
     * 包含响应头,响应体数据
     */
    @Override
    public String getForEntityForAll() {
        String uri = "http://localhost:8080/city";
        ResponseEntity<Object> actorResponseEntity = null;
        try {
            actorResponseEntity = restTemplate.getForEntity(uri, Object.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        String cityListStr = JSON.toJSONString(actorResponseEntity);
        return cityListStr;
    }

    /**
     * getForObject()
     * 返回的只有实体数据,没有响应头的数据
     */
    @Override
    public String getForObjectForAll() {
        String uri = restServerProperties.getUrl() + "/city";
        Object object = restTemplate.getForObject(uri, Object.class);
        String cityListStr = JSON.toJSONString(object);
        logger.info("cityListStr:{}" + cityListStr);
        return cityListStr;
    }


    /**
     * getForEntity()
     * 获取数据-参数
     */
    public City getForEntityByCityId(Long cityId) {
        String uri = "http://localhost:8080/city/" + cityId;
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);

        //Body是LinkHashMap类型
        /*Object body = responseEntity.getBody();
        String botyStr = JSON.toJSONString(body);
        City city = JSON.parseObject(botyStr, City.class);*/

        //从响应体中拿取实体类数据
        City city = JSON.parseObject(JSON.toJSONString(responseEntity.getBody()), City.class);

        return city;
    }

    /**
     * postForLocation()
     * 添加数据
     */
    public String postForLocationForCity(City city) {
        String uriStr = null;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(city), headers);

        String url = restServerProperties.getUrl() + "/city";

        //rest服务中Controller方法使用对象无法接收参数
//        URI uri = restTemplate.postForLocation(url, city);
        //rest服务中Controller方法中 @RequestBody 注解不支持默认的text/plain;charset=ISO-8859-1
//        URI uri = restTemplate.postForLocation(url, JSON.toJSONString(city));

        //1.只能发送对象字符串,否则Rest服务接收不到参数
        //2.字符串默认数据类型和编码是text/plain;charset=ISO-8859-1,
        // 调用的Rest服务的Controller方法中使用 @RequestBody 解析参数封装到对象中,
        // 而@RequestBody注解解析的是JSON数据,所以需要设置消息头告诉数据类型和编码
        URI uri = restTemplate.postForLocation(url, formEntity);
        if(uri != null){
            uriStr = JSON.toJSONString(uri);
        }
        return uriStr;
    }

    /**
     * postForEntity()
     * 添加数据
     */
    public String postForEntityForCity(City city) {
        String url = restServerProperties.getUrl() + "/city";
        ResponseEntity<City> cityResponseEntity = restTemplate.postForEntity(url, city, City.class);
        String str = JSON.toJSONString(cityResponseEntity);
        return str;
    }

    /**
     * postForObject()
     * 添加数据
     */
    public String postForObjectForCity(City city) {
        String url = restServerProperties.getUrl() + "/city";
        City city1 = restTemplate.postForObject(url, city, City.class);
        String str = JSON.toJSONString(city1);
        return str;
    }

    /**
     * put()
     * 添加数据
     */
    public void putForCity(Long cityId, String cityName) {
        String url = restServerProperties.getUrl() + "/city" + "/" + cityId + "/" + cityName;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(
                new City().setCityId(cityId).setCityName(cityName)), headers);
        restTemplate.put(url, formEntity);
    }

















/*    @Override
    public String getUserById(Long id) {
        String object = restTemplate.getForObject("http://localhost:8080/user/queryByActorId", String.class, 1L);
        return object;
    }*/

    /*@Override
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
        };*/

//        Object execute = restTemplate.execute(uri, HttpMethod.GET, requestCallback, actorResponseEntity);

/*        String str = "http://localhost:8080/actor/add";
//        ?actorId=3001&firstName=Robin&lastName=Zhang
        URI uri = new URI(str);
        HttpEntity<Actor> actorHttpEntity = new RequestEntity<Actor>(HttpMethod.GET,uri);
        ResponseEntity<Actor> actorResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, actorHttpEntity, Actor.class);*/

    /*System.out.println(JSON.toJSONString(execute));*/

//        return 1;
//    }

    //    @Override
    public int queryCount() {
        return restTemplate.getForObject("http://localhost:8080/user/queryCount", Integer.class);
    }


}
