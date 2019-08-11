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

import java.util.Date;

@RestController
@RequestMapping("/remote")
public class CallRemoteServiceController {

    private final static Logger logger = LogManager.getLogger(CallRemoteServiceController.class);

    @Autowired
    private CallRemoteService callRemoteService;

    @RequestMapping("/hello")
    public String returnHello() {
        return "Hello World";
    }

    /**
     * 查所有
     *
     * @return
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public String queryAll() {
        //直接返回对象数据
        String cityListStr1 = callRemoteService.getForObject();
        logger.info("cityListStr1:{}", cityListStr1);

        //需要的数据包状态 body 中
        String cityListStr2 = callRemoteService.getForEntity();
        logger.info("cityListStr2:{}", cityListStr2);
        return cityListStr2;
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
     */
    @RequestMapping(value = "/addCity", method = RequestMethod.POST)
    public String postForCity() {
        City city1 = new City(601L, "杭州", 83L, new Date());
        City city2 = new City(602L, "苏州", 83L, new Date());
        City city3 = new City(603L, "吴州", 83L, new Date());

        String str1 = callRemoteService.postForLocationForCity(city1);
        String str2 = callRemoteService.postForEntityForCity(city2);
        String str3 = callRemoteService.postForObjectForCity(city3);

        logger.info("str:{}", str3);
        return str3;

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
