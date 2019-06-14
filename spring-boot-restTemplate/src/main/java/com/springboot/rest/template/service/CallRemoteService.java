package com.springboot.rest.template.rest.template.service;

import com.springboot.rest.template.rest.template.entity.City;

public interface CallRemoteService {

    String getForObjectForAll();

    String getForEntityForAll();

    City getForEntityByCityId(Long cityId);

    String postForLocationForCity(City city);

    String postForEntityForCity(City city);

    String postForObjectForCity(City city);

    void putForCity(Long cityId, String cityName);

    void deleteById(Long cityId);
}
