package com.qizhidao.ecloud.history.contract.model.doo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description 省市区实体
 * @Author lizhicheng
 * @Date 2019-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "City对象", description = "")
public class CityDO {


    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "省市区名称")
    private String name;

    @ApiModelProperty(value = "上级ID")
    private Integer parentid;

    @ApiModelProperty(value = "简称")
    private String shortname;

    @ApiModelProperty(value = "级别:0,中国；1，省分；2，市；3，区、县")
    private Integer leveltype;

    @ApiModelProperty(value = "城市代码")
    private String citycode;

    @ApiModelProperty(value = "邮编")
    private String zipcode;

    @ApiModelProperty(value = "经度")
    private String lng;

    @ApiModelProperty(value = "纬度")
    private String lat;

    @ApiModelProperty(value = "拼音")
    private String pinyin;

    private String status;

    @ApiModelProperty(value = "七大区域,1：华东，2华北，3华中，4华南，5西南，6西北，7东北，8台港澳地区")
    private Boolean belongRegion;

    @ApiModelProperty(value = "区域名称 ")
    private String belongRegionName;

    @ApiModelProperty(value = "是否直辖市 0 否，1是")
    private Integer isDirect;

    @ApiModelProperty(value = "上级")
    private CityDO parent;

    @ApiModelProperty(value = "是否数据权限控制")
    private Integer permission;
}
