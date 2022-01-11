package com.gxitsky.controller;

import com.alibaba.fastjson.JSON;
import com.gxitsky.entity.City;
import com.gxitsky.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {
    private static final Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    /**
     * 查所有
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<City> queryAll() {
        List<City> cityList = cityService.queryAll();
        logger.info("cityList:{}", JSON.toJSONString(cityList));
        return cityList;
    }

    /**
     * 条件查询
     *
     * @return
     */
    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public City queryByCityId(@PathVariable String cityId) {
        return cityService.queryByCityId(Long.valueOf(cityId));
    }

    /**
     * 新增,如果数据存在,则会变为更新
     * 备注：提供的POST方法Rest接口供RestTemplate调用时,接收参数只能是JSON字符串
     */
    @RequestMapping(method = RequestMethod.POST)
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    /**
     * 修改
     * 问题:哪果url路径中是中文参数,则会报错:
     * Invalid character found in the request .target. The valid characters are defined in RFC 7230 and RFC 3986
     * 需要对url路径中的中文进编码
     * http://localhost:8080/city/800/深圳   编码后: http://localhost:8080/city/800/%e6%b7%b1%e5%9c%b3
     */
    @RequestMapping(value = "/{cityId}/{cityName}", method = RequestMethod.PUT)
    public int updateCity(@PathVariable Long cityId, @PathVariable String cityName) {
        return cityService.updateCity(cityId, cityName);
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.DELETE)
    public void deleteCityById(@PathVariable Long cityId) {
        cityService.deleteCityById(cityId);
    }

}
