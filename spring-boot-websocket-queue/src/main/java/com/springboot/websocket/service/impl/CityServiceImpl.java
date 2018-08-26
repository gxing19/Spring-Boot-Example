package com.springboot.websocket.service.impl;

import com.springboot.websocket.entity.City;
import com.springboot.websocket.repository.CityRepository;
import com.springboot.websocket.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> queryAll() {
        List<City> cityList = cityRepository.findAll();
        return cityList;
    }

    @Override
    public City queryById(Long cityId) {
//        return cityRepository.findOne(cityId);            //springboot 1.5.x.Release
//        return cityRepository.findById(cityId).get();       //springboot 2.0.x.Release
        return null;
    }
}
