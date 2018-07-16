package com.springboot.rest.template.service;

import com.springboot.rest.template.entity.City;

import java.net.URI;
import java.util.List;

public interface CallRemoteService {

    String getForObjectForAll();

    String getForEntityForAll();

    City getForEntityByCityId(Long cityId);

    String postForLocationForCity(City city);

    String postForEntityForCity(City city);

    String postForObjectForCity(City city);

    void putForCity(Long cityId, String cityName);
}
