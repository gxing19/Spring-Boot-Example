package com.springboot.jpa.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.jpa.entity.City;
import com.springboot.jpa.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/city", method = RequestMethod.POST)
public class CityController {
    private static final Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @RequestMapping("/queryAll")
    public List<City> queryAll() {
        List<City> cityList = cityService.queryAll();
        logger.info("cityList:{}", JSON.toJSONString(cityList));
        return cityList;
    }

    @RequestMapping("/queryById")
    public City queryById(Long cityId) {
        City city = cityService.queryById(cityId);
        logger.info("city:{}", JSON.toJSONString(city));
        return city;
    }
}
