package com.springboot.restful.service;

import com.springboot.restful.entity.City;

import java.util.List;

public interface CityService {
    List<City> queryAll();

    City queryByCityId(Long cityId);

    City addCity(City city);

    int updateCity(Long cityId, String cityName);
}
