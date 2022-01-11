package com.gxitsky.service.impl;

import com.gxitsky.entity.City;
import com.gxitsky.repository.CityRepository;
import com.gxitsky.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
//        return cityRepository.findOne(cityId);            //springboot 1.5.3.Release
        return cityRepository.findById(cityId).get();       //springboot 2.0.0.Release及以上
    }
}
