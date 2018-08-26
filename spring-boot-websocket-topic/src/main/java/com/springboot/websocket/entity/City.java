package com.springboot.websocket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "city")
public class City {
    @Id
    private Long cityId;
    private String city;
    private Long countryId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastUpdate;

    public Long getCityId() {
        return cityId;
    }

    public City setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public City setCity(String city) {
        this.city = city;
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
