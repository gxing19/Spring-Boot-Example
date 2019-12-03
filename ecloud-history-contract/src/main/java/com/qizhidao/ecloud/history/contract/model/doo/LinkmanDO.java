package com.qizhidao.ecloud.history.contract.model.doo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description 客户联系人实体
 * @Author lizhicheng
 * @Date 2019/2/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class LinkmanDO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("联系人姓名")
    private String linkmanName;

    @ApiModelProperty("联系人类型")
    private String linkmanType;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("电子邮件")
    private String email;

    @ApiModelProperty("微信号")
    private String wechatNumber;

    @ApiModelProperty("qq号")
    private String qqNumber;

    @ApiModelProperty("0公历,1农历")
    private Integer calendarFlag;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("客户id")
    private Long custId;

    @ApiModelProperty("职位")
    private String post;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("联系邮编")
    private String zipCode;

    @ApiModelProperty("联系地区-省")
    private String provinceCode;

    @ApiModelProperty("联系地区-市")
    private String cityCode;

    @ApiModelProperty("联系地区-区")
    private String countyCode;

    @ApiModelProperty("联系地区-省")
    private String provinceName;

    @ApiModelProperty("联系地区-市")
    private String cityName;

    @ApiModelProperty("联系地区-区")
    private String countyName;

    @ApiModelProperty("联系详细地址")
    private String detailAddress;
}
