package com.qizhidao.ecloud.history.contract.model.doo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description 国家实体类
 * @Author julius
 * @Date 2019/2/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CountryDO", description = "国家地区实体")
public class CountryDO {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "表主键")
    private Integer id;

    /**
     * 国家编码
     */
    @ApiModelProperty(value = "国家编码")
    private String countyCode;

    /**
     * 国家名称
     */
    @ApiModelProperty(value = "国家名称")
    private String countyName;

    /**
     * 币种主键
     */
    @ApiModelProperty(value = "币种主键")
    private Integer currencyId;

    /**
     * 状态: 0-正常; 1-删除
     */
    @ApiModelProperty(value = "状态: 0-正常; 1-删除")
    private Integer status;

    /**
     * 组织结构树
     */
    @ApiModelProperty(value = "组织结构树")
    private String treeCode;

    /**
     * 币种名称
     */
    @ApiModelProperty(value = "币种名称")
    private String currencyName;

    @ApiModelProperty(value = "币种CODE")
    private String currencyCode;
}
