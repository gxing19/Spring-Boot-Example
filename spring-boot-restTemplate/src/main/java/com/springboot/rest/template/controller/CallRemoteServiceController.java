package com.springboot.rest.template.controller;


import com.alibaba.fastjson.JSON;
import com.springboot.rest.template.entity.City;
import com.springboot.rest.template.service.CallRemoteService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/remote")
public class CallRemoteServiceController {

    private final static Logger logger = LogManager.getLogger(CallRemoteServiceController.class);

    @Autowired
    private CallRemoteService callRemoteService;

    @RequestMapping("/hello")
    public String returnHello(){
        return "Hello World";
    }

    /**
     * 查所有
     * @return
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public String queryAll(){
//        String cityList = callRemoteService.getForObjectForAll();
        String cityListStr = callRemoteService.getForEntityForAll();
        logger.info("cityListStr:{}", JSON.toJSONString(cityListStr));
        return cityListStr;
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public City queryByCityId(@PathVariable Long  cityId){
        City city = callRemoteService.getForEntityByCityId(cityId);
        logger.info("city:{}", JSON.toJSONString(city));
        return city;
    }

    /**
     * post添加
     * postForLocation,postForEntity,postForObject
     * @param city
     */
    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public String postForCity(City city){
//        String str = callRemoteService.postForLocationForCity(city);
//        String str = callRemoteService.postForEntityForCity(city);
        String str = callRemoteService.postForObjectForCity(city);

        logger.info("str:{}", str);
        return str;

    }

    @RequestMapping(value = "/{cityId}/{cityName}", method = RequestMethod.PUT)
    public void putForCity(@PathVariable Long cityId, @PathVariable String cityName){
        callRemoteService.putForCity(cityId, cityName);
    }

}
