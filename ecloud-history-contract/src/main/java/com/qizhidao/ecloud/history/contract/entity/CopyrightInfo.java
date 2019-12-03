package com.qizhidao.ecloud.history.contract.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class CopyrightInfo {
    /**
     *   表主键
     */
    private Long copyrightInfoId;

    /**
     *   ips_cr_copyright_t表主键
     */
    private Long copyrightId;

    /**
     *   软件全称
     */
    private String fullName;

    /**
     *   软件简称
     */
    private String shortName;

    /**
     *   分类号
     */
    private String classifyNo;

    /**
     *   版本号
     */
    private String versionNo;

    /**
     *   作品类型(1=原创,2=修改)
     */
    private Integer workType;

    /**
     *   开发完成日期
     */
    private LocalDate devCompletionDate;

    /**
     *   发表状态(1=已发表,2=未发表)
     */
    private Integer publishStatus;

    /**
     *   发表日期
     */
    private LocalDate publishDate;

    /**
     *   发表地点
     */
    private Long publishPlaceId;

    /**
     *   城市
     */
    private String city;

    /**
     *   开发方式(1=独立开发,2=合作开发,3=委托开发,4=下达任务开发)
     */
    private Integer devMode;

    /**
     *   是否加急(Y=加急,N=普通)
     */
    private String urgentFlag;

    /**
     *   加急时间
     */
    private String urgentDate;

    /**
     *   有无源码(Y=有源码,N=无源码)
     */
    private String sourceCodeFlag;

    /**
     *   申请流水号
     */
    private String applyFlowNo;

    /**
     *   创建人
     */
    private Long createBy;

    /**
     *   创建时间
     */
    private Date createDate;

    /**
     *   最后更新人
     */
    private Long lastUpdateBy;

    /**
     *   最后更新时间
     */
    private Date lastUpdateDate;

    /**
     *   软件作品说明(备注)
     */
    private String workDescription;
    /**
     * 编写方
     */
    private String writerMan;
    /**
     * 编写费
     */
    private String writerFee;
    /**
     * 编写时间
     */
    private LocalDate writeDate;

    /**
     * 登记号
     */
    private String registerNo;



}