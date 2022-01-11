package com.gxitsky.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "city")
public class City {
    @Id
    private Long cityId;
    @Column(name = "city")
    private String cityName;
    private Long countryId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;

    public City() {
    }

    public City(Long cityId, String cityName, Long countryId, Date lastUpdate) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.countryId = countryId;
        this.lastUpdate = lastUpdate;
    }

    public Long getCityId() {
        return cityId;
    }

    public City setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public City setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public Long getCountryId() {
        return countryId;
    }

    public City setCountryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public City setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }
}
