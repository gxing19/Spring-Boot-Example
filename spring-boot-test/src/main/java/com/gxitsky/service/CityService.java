package com.gxitsky.service;

import com.gxitsky.entity.City;

import java.util.List;

public interface CityService {
    List<City> queryAll();

    City queryById(Long cityId);
}
