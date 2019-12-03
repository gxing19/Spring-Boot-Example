package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightWorkTransferDetail", description = "作品版权转让-详情表")
public class CopyrightWorkTransferDetail {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "版权转让详情ID")
    private Long id;

    /**
     *   ips_cr_work_transfer_info主键
     */
    @ApiModelProperty(value = "版权转让ID")
    private Long workTransferId;

    /**
     *   ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    /**
     *   1许可方信息,2受让方信息
     */
    @ApiModelProperty(value = "1许可方信息,2受让方信息")
    private Integer transferType;

    /**
     *   名字
     */
    @ApiModelProperty(value = "名字")
    private String name;

    /**
     *   lookup:01自然人02企业法人03机关法人04事业单位法人05社会团体法人06其他组织07其他
     */
    @ApiModelProperty(value = "人员类别lookup")
    private String manType;

    /**
     *   lookup:01居民身份证02军官证03营业执照04护照05企业法人营业执照06组织机构代码证书07事业单位法人证书08社团法人证书09其他有效证件
     */
    @ApiModelProperty(value = "身份证类别lookup")
    private String idType;

    /**
     *   证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String idNo;

    /**
     *   国籍
     */
    @ApiModelProperty(value = "国籍")
    private String countryCode;
    private String countryName;

    /**
     *   省
     */
    @ApiModelProperty(value = "省")
    private Integer provinceCode;
    private String provinceName;

    /**
     *   市
     */
    @ApiModelProperty(value = "市")
    private Integer cityCode;
    private String cityName;

    /**
     *   区
     */
    @ApiModelProperty(value = "区")
    private Integer regionCode;
    private String regionName;

    /**
     *   地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     *   创建人
     */
    private Long createdBy;

    /**
     *   创建时间
     */
    private Date creationDate;

    /**
     *   最后更新人
     */
    private Long lastUpdatedBy;

    /**
     *   修改时间
     */
    private Date lastUpdateDate;
}