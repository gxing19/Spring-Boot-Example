package com.gxitsky.controller;

import com.alibaba.fastjson.JSON;
import com.gxitsky.entity.City;
import com.gxitsky.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    private static final Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @RequestMapping("/queryAll")
    public List<City> queryAll() {
        //生成LogId,以时间毫秒值
        ThreadContext.put("LogId", Long.toString(System.currentTimeMillis()));
        List<City> cityList = cityService.queryAll();
        logger.info("cityList:{}", JSON.toJSONString(cityList));
        ThreadContext.remove("LogId");
        return cityList;
    }

    @RequestMapping("/queryById")
    public City queryById(Long cityId) {
        //生成LogId,以线程ID
        ThreadContext.put("LogId", Long.toString(Thread.currentThread().getId()));
        City city = cityService.queryById(cityId);
        logger.info("city:{}", JSON.toJSONString(city));
        ThreadContext.remove("LogId");
        return city;
    }
}
