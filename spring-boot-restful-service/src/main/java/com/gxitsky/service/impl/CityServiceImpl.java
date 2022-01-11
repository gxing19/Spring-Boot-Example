package com.gxitsky.service.impl;

import com.gxitsky.entity.City;
import com.gxitsky.repository.CityRepository;
import com.gxitsky.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> queryAll() {
        List<City> cityList = cityRepository.findAll();
        return cityList;
    }

    @Override
    public City queryByCityId(Long cityId) {
//        return cityRepository.findOne(cityId);            //springboot 1.5.3.Release
        return cityRepository.findById(cityId).get();       //springboot 2.0.0.Release及以上
    }

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public int updateCity(Long cityId, String cityName) {
//        return cityRepository.save(new City().setCityId(cityId).setCityName(cityName));
        return cityRepository.update(cityId, cityName);
    }

    @Override
    public void deleteCityById(Long cityId) {
        cityRepository.deleteById(cityId);

    }

}
