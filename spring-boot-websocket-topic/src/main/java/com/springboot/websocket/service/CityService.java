package com.springboot.websocket.service;

import com.springboot.websocket.entity.City;

import java.util.List;

public interface CityService {
    List<City> queryAll();

    City queryById(Long cityId);
}
