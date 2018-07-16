package com.springboot.web.service;

import com.springboot.web.entity.City;

import java.util.List;

public interface CityService {
    List<City> queryAll();

    City queryById(Long cityId);
}
