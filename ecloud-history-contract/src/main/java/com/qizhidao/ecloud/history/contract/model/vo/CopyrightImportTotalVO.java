package com.qizhidao.ecloud.history.contract.model.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 软著登记导入
 */
@Data
@Accessors(chain = true)
public class CopyrightImportTotalVO extends CopyrightImportBaseVO implements Serializable {

    private static final long serialVersionUID = -3534393574049398816L;

    //=============================以下基础信息=========================
    /**
     * 软件全称
     */
    private String fullName;

    /**
     * 软件简称
     */
    private String shortName;

    /**
     * 分类号
     */
    private String classifyNo;

    /**
     * 版本号
     */
    private String versionNo;

    /**
     * 作品类型(1=原创,2=修改)
     */
    private Integer workType;

    /**
     * 作品说明(备注)
     */
    private String workDescription;

    /**
     * 开发完成日期
     */
    private Date devCompletionDate;

    /**
     * 开发方式(1=独立开发,2=合作开发,3=委托开发,4=下达任务开发)
     */
    private Integer devMode;

    //===============================以下是发表状态==============================

    /**
     * 发表状态(1=已发表,2=未发表)
     */
    private Integer publishStatus;

    /**
     * 发表日期
     */
    private Date publishDate;

    /**
     * 发表地点-国家
     */
    @Excel(name = "publishCountry")
    private String publishCountry;
    private Long publishPlaceId;

    /**
     * 发表城市
     */
    @Excel(name = "publishCity")
    private String publishCity;
    private String city;

    //===============================以下是源码信息============================
    /**
     * 有无源码("有源码_Y", "无源码_N", "无(可运行)_M")
     */
    private String sourceCodeFlag;

    /**
     * 编写方
     */
    private String writerMan;

    /**
     * 编写时间
     */
    private Date writeDate;

    //==============================以下成本信息===============================

    /**
     * 编写费
     */
    private String writerFee;

    /**
     * 官费
     */
    private BigDecimal officialFee;

    /**
     * 加急费
     */
    private BigDecimal urgentFee;

    //==============================以下著作权人信息============================

    /**
     * 姓名/单位名称
     */
    private String name;

    /**
     * 著作权人类型
     */
    private String copyrightTypeName;
    private Integer copyrightType;

    /**
     * 国家名称
     */
    private String countryName;
    private Long countryId;

    /**
     * 省份
     */
    private String provinceName;
    private Integer provinceId;

    /**
     * 城市
     */
    private String cityName;
    private Integer cityId;

    /**
     * 证件类型
     */
    private String idTypeName;
    private Integer idType;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 署名情况(0=本名,1=别名,2=匿名)
     */
    private String signature;

    //==============================以下软件技术特点============================
    /**
     * 软件技术主键
     */
    private Long technologyId;
    /**
     * 硬件环境
     */
    private String hardware;

    /**
     * 软件环境
     */
    private String software;

    /**
     * 编程语言
     */
    private String programLanguage;

    /**
     * 源程序量
     */
    private String sourceProgram;

    /**
     * 主要功能和技术特点
     */
    private String description;

    //==============================以下作品基础信息=========================
    /**
     * 作品名称
     */
    private String workName;

    /**
     * 作品说明(变更理由说明)
     */
    private String changeDescription;

    /**
     * 作者姓名或名称
     */
    private String author;

    /**
     * 作口署名
     */
    private String workSignature;

    /**
     * 1原创2改编3翻译4汇编5注释6整理7其他
     */
    private Integer workCreationQuality;

    /**
     * 原创说明
     */
    private String originalDesc;

    /**
     * 创作完成日期
     */
    private Date creationCompletionDate;

    /**
     * 创作完成地点
     */
    private String creationCompletionCode;

    /**
     * 创作完成城市
     */
    private String creationCompletionCity;

    /**
     * 保留作品样本：0电子介质，1纸介质
     */
    private String sampleType;

    /**
     * 保留作品样本介质类型
     */
    private String sampleTypeDetail;

    /**
     * 保留样本纸介质 件数
     */
    private Integer sampleCount;

    //============================软著变更基础信息========================
    /**
     * 原登记类别:0-软件著作权登记, 1-软件著作权转让合同登记, 2软件著作权专有许可合同登记
     */
    private Integer oldRegisterType;

    /**
     * 原变更/补充登记证明编号
     */
    private String oldCertifyNo;

    /**
     * 著作权人
     */
    private String copyrightOwner;

    /**
     * 申请类型(A01=变更登记,A02=补充登记)
     */
    private String applyType;

    //==============================软件评测基础信息=============================

    /**
     * 开发单位
     */
    private String developmentUnit;

    /**
     * 软件类别：1=嵌入式软件,2=非嵌入式软件
     */
    private int softwareType;

    /**
     * 评测机构
     */
    private String testOrganization;

    /**
     * 评测费用
     */
    private String testFee;

    //=================================以下软著转让特有信息=====================
    /**
     * 合同类型:1转让合同，2专有许可合同，3非专有许可合同
     */
    private Integer contractType;

    /**
     * 权利取得方式 1-受让,2-许可
     */
    private Integer gainRightWay;

    /**
     * 转让合同生效日期
     */
    private Date contractEffectiveDate;

    /**
     * 许可权利期限  开始
     */
    private Date startPermissionDate;
    /**
     * 许可权利期限  结束
     */
    private Date endPermissionDate;

    //==============================软著撤销特有属性=============================
    /**
     * 登记类别:0-软件著作权登记,1-软件著作权转让合同登记,2-软件著作权专有许可合同登记"
     */
    private Integer registerType;

    /**
     * 登记日期
     */
    private Date registerDate;

    /**
     * 撤消理由
     */
    private String revokeReason;

    /**
     * 有无共同登记人：Y有，N否
     */
    private String commonRegisterFlag;

    /**
     * 共同登记人中称
     */
    private String commonRegisterPerson;

    /**
     * 共同登记人意见
     */
    private String commonRegisterSuggest;

    //===========================软著补换证书特有属性======================
    /**
     * 补换证书类型:1-软件著作权登记证书，2-软件合同登记证书，3-变更补充证明
     */
    private Integer replacementCertificateType;

    /**
     * 补发或者换发的理由
     */
    private String replacementCertificateReason;
    //==========================作品补换证书==============================

    /**
     * 原登记号(登记号)
     */
    private String oldRegisterNo;

    /**
     * 原登记日期
     */
    private Date oldRegisterDate;

    /**
     * 原登记类型lookup:01作品著作权登记02著作权合同备案03与著作权有关权利事项登记04重印国外期刊合同登记05著作权质权登记
     */
    private String workReplaceOldRegisterType;

    /**
     * 补换证书类型:1登记证书，2变更或补充证明，3复制样本_
     */
    private Integer workReplacementCertificateType;

    /**
     * 补发或者换发的理由
     */
    private String workReplacementCertificateReason;
    //============================软著查询特有属性===============================
    /**
     * 权利取得方式:1原始取得，2继受取得，3承受取得，4其他
     */
    private Integer rightsAcquiringWay;

    /**
     * 查询人性质:1软件登记人,2司法机关
     */
    private String queryType;

    /**
     * 查询目的：[1转让2承受3合同4撤销5变更补充6补发7复印原始档案8其他],2司法机关[1司法机关调查取证2司法机关查封或解封3其他]
     */
    private String queryPurpose;

    /**
     * 其他说明
     */
    @Excel(name = "otherInfo")
    private String otherInfo;

    //========================作品查询特有字段===================================
    /**
     * 查询人性质:1作品登记人,2司法机关或行政管理机关
     */
    private String workQueryType;

    /**
     * 1作品登记人[1质权登记2质权注销3其他],2司法机关或行政管理机关[1司法、行政机关调查取证2其他]
     */
    private String workQueryPurpose;

    /**
     * 其他说明
     */
    @Excel(name = "workOtherInfo")
    private String workOtherInfo;

    /**
     * 查询结果领取方式:1邮寄，2自取
     */
    private Integer resultReceiveWay;

    /**
     * 原登记类别:0作品著作权登记,1著作合同备案,2与著作权有关权利事项登记，3重印国外期刊合同登记
     */
    private Integer workChangeOldRegisterType;

    /**
     * 原受理号
     */
    @Excel(name = "referenceNo")
    private String referenceNo;

    //============================作品撤销========================

    /**
     * 撤消依据:1申请人申请撤销、2第三方申请撤销、3其他
     */
    private String revokeBasis;

    //==========================作品转让===========================
    /**
     * 作品数量
     */
    private String workCount;

    /**
     * 合同生效日期开始时间
     */
    private Date startEffectiveDate;

    /**
     * 合同生效日期结束时间
     */
    private Date endEffectiveDate;

    /**
     * 是否永久:N否，Y是
     */
    private String permanentFlag;


}
