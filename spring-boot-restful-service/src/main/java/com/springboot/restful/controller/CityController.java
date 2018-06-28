package com.springboot.restful.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.restful.entity.City;
import com.springboot.restful.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {
    private static final Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    /**
     * 查所有
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<City> queryAll(){
        List<City> cityList = cityService.queryAll();
        logger.info("cityList:{}", JSON.toJSONString(cityList));
        return cityList;
    }

    /**
     * 条件查询
     * @return
     */
    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public City queryByCityId(@PathVariable Long cityId){
        return cityService.queryByCityId(cityId);
    }

    /**
     * 新增
     */
    @RequestMapping(method = RequestMethod.POST)
    public City addCity(@RequestBody  City city){
        city.setLastUpdate(new Date());
       return cityService.addCity(city);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/{cityId}/{city}", method = RequestMethod.PUT)
    public City updateCity(@PathVariable Long cityId, @PathVariable String city){
        return cityService.updateCity(cityId,city);
    }
}
