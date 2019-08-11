package com.springboot.rest.template.controller;


import com.alibaba.fastjson.JSON;
import com.springboot.rest.template.entity.City;
import com.springboot.rest.template.service.CallRemoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/remote")
public class CallRemoteServiceController {

    private final static Logger logger = LogManager.getLogger(CallRemoteServiceController.class);

    @Autowired
    private CallRemoteService callRemoteService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String returnHello() {
        return "Hello World";
    }

    /**
     * RestTemplate get方法
     *
     * @return
     */
    @RequestMapping(value = "/getMethod", method = RequestMethod.GET)
    public String getMethod() {
        String cityListStr = callRemoteService.getMethod();
        logger.info("cityListStr:{}", JSON.toJSONString(cityListStr));
        return cityListStr;
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public City queryByCityId(@PathVariable Long cityId) {
        City city = callRemoteService.getForEntityByCityId(cityId);
        logger.info("city:{}", JSON.toJSONString(city));
        return city;
    }

    /**
     * post添加
     * postForLocation,postForEntity,postForObject
     *
     * @param city
     */
    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public String postForCity(City city) {
//        String str = callRemoteService.postForLocationForCity(city);
//        String str = callRemoteService.postForEntityForCity(city);
        String str = callRemoteService.postForObjectForCity(city);

        logger.info("str:{}", str);
        return str;

    }

    /**
     * put更新
     *
     * @param cityId
     * @param cityName
     */
    @RequestMapping(value = "/{cityId}/{cityName}", method = RequestMethod.PUT)
    public void putForCity(@PathVariable Long cityId, @PathVariable String cityName) {
        callRemoteService.putForCity(cityId, cityName);
    }


    /**
     * delete删除
     *
     * @param cityId
     */
    @RequestMapping(value = "/{cityId}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long cityId) {
        callRemoteService.deleteById(cityId);
    }


}
