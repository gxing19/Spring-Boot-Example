package com.springboot.restful.repository;

import com.springboot.restful.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Modifying
    @Transactional
    @Query("update City set cityName = :cityName where  cityId = :cityId")
    int update(@Param("cityId") Long cityId, @Param("cityName") String cityName);

    /*@Modifying
    @Transactional
    @Query("update City set cityName = ?2 where  cityId = ?1")
    int update(Long cityId, String cityName);*/
}
