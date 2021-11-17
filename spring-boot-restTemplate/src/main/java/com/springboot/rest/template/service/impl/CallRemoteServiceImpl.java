package com.springboot.rest.template.service.impl;

import com.alibaba.fastjson.JSON;
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
import java.util.HashMap;
import java.util.Map;

@Service
public class CallRemoteServiceImpl implements CallRemoteService {

    private static final Logger logger = LogManager.getLogger(CallRemoteServiceImpl.class);

    @Autowired
    private RestServerProperties restServerProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * getForObject()
     * 返回的只有实体数据,没有响应头的数据
     */
    @Override
    public String getForObject() {
        String cityListStr = restTemplate.getForObject("http://localhost:8080/city", String.class);
        logger.info("cityListStr:{}" + cityListStr);
        return cityListStr;
    }

    /**
     * getForEntity()
     * 获取数据-所有
     * 包含响应头,响应体数据
     */
    @Override
    public String getForEntity() {
        ResponseEntity<String> entity = null;
        try {
            entity = restTemplate.getForEntity("http://localhost:8080/city", String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        String body = entity.getBody();

        return body;
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public String postForLocation() {
        return null;
    }


    /**
     * 路径传参
     * getForEntity()
     * 获取数据-参数
     */
    public City getForEntityByCityId(Long cityId) {
        City city1 = restTemplate.getForObject("http://localhost:8080/city/" + cityId, City.class);
        ResponseEntity<City> responseEntity2 = restTemplate.getForEntity("http://localhost:8080/city/" + cityId, City.class);

        //Body是LinkHashMap类型
        /*Object body = responseEntity.getBody();
        String botyStr = JSON.toJSONString(body);
        City city = JSON.parseObject(botyStr, City.class);*/

        //从响应体中拿取实体类数据
        City city2 = JSON.parseObject(JSON.toJSONString(responseEntity2.getBody()), City.class);


        Map<String, Long> varMap = new HashMap<>(1);
        varMap.put("cityId", cityId);
        //getForObject方法
        City city3 = restTemplate.getForObject("http://localhost:8080/city/{cityId}", City.class, varMap);
        //getForEntity方法
        ResponseEntity<City> responseEntity4 = restTemplate.getForEntity("http://localhost:8080/city/{cityId}", City.class, varMap);
        City city4 = responseEntity2.getBody();

        return city4;
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

        String url = "http://localhost:8080/city";

        //rest服务中Controller方法使用对象无法接收参数
//        URI uri = restTemplate.postForLocation(url, city);
        //rest服务中Controller方法中 @RequestBody 注解不支持默认的text/plain;charset=ISO-8859-1
//        URI uri = restTemplate.postForLocation(url, JSON.toJSONString(city));

        //1.只能发送对象字符串,否则Rest服务接收不到参数
        //2.字符串默认数据类型和编码是text/plain;charset=ISO-8859-1,
        // 调用的Rest服务的Controller方法中使用 @RequestBody 解析参数封装到对象中,
        // 而@RequestBody注解解析的是JSON数据,所以需要设置消息头告诉数据类型和编码
        URI uri = restTemplate.postForLocation(url, formEntity);
        if (uri != null) {
            uriStr = JSON.toJSONString(uri);
        }
        return uriStr;
    }

    /**
     * postForEntity()
     * 添加数据
     */
    public String postForEntityForCity(City city) {
        String url = "http://localhost:8080/city";
        ResponseEntity<City> cityResponseEntity = restTemplate.postForEntity(url, city, City.class);
        String str = JSON.toJSONString(cityResponseEntity);
        return str;
    }

    /**
     * postForObject()
     * 添加数据
     */
    public String postForObjectForCity(City city) {
        String url = "http://localhost:8080/city";
        City city1 = restTemplate.postForObject(url, city, City.class);
        String str = JSON.toJSONString(city1);
        return str;
    }

    /**
     * put()
     * 更新数据
     */
    public void putForCity(Long cityId, String cityName) {
        String url = restServerProperties.getUrl() + "/city" + "/" + cityId + "/" + cityName;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(
                new City().setCityId(cityId).setCityName(cityName)), headers);
        restTemplate.put(url, null);
    }

    /**
     * delete删除
     *
     * @param cityId
     */
    @Override
    public void deleteById(Long cityId) {
        String url = restServerProperties.getUrl() + "/city" + "/" + cityId;
        restTemplate.delete(url);
    }

    public void restTemplateTest() {

//        restTemplate.getForObject()
    }

}
