package com.springboot.jpa.service;

import com.springboot.jpa.entity.City;

import java.util.List;

public interface CityService {
    List<City> queryAll();

    City queryById(Long cityId);
}
