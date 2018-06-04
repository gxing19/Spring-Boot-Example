package com.springboot.example.jpa.service;

import com.springboot.example.jpa.entity.City;

import java.util.List;

public interface CityService {
    List<City> queryAll();

    City queryById(Long cityId);
}
