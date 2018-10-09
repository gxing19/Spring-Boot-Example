package com.springboot.springjms.service;

import com.springboot.springjms.entity.City;

import java.util.List;

public interface CityService {
    List<City> queryAll();

    City queryById(Long cityId);
}
