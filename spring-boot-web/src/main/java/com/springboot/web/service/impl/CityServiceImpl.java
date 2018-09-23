package com.springboot.web.service.impl;

import com.springboot.web.entity.City;
import com.springboot.web.repository.CityRepository;
import com.springboot.web.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger logger = LogManager.getLogger(CityServiceImpl.class);

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> queryAll() {
        logger.info("queryAll:{}", "queryAll");
        List<City> cityList = cityRepository.findAll();
        return cityList;
    }

    @Override
    public City queryById(Long cityId) {
        logger.info("queryById:{}", "城市ID...........");
//        return cityRepository.findOne(cityId);            //springboot 1.5.x.Release
        return cityRepository.findById(cityId).get();       //springboot 2.0.x.Release
    }
}
