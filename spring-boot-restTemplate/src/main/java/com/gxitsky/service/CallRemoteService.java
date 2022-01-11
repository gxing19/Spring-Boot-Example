package com.gxitsky.service;

import com.gxitsky.entity.City;

public interface CallRemoteService {

    String getForObject();

    String getForEntity();

    String getMethod();

    String postForLocation();

    City getForEntityByCityId(Long cityId);

    String postForObjectForCity(City city);

    String postForLocationForCity(City city);

    String postForEntityForCity(City city);

    void putForCity(Long cityId, String cityName);

    void deleteById(Long cityId);
}
