package com.springboot.rest.template.service;

import com.springboot.rest.template.entity.City;

public interface CallRemoteService {

    String getForObject();

    String getForEntity();

    String postForLocation();

    City getForEntityByCityId(Long cityId);

    String postForObjectForCity(City city);

    void putForCity(Long cityId, String cityName);

    void deleteById(Long cityId);
}
