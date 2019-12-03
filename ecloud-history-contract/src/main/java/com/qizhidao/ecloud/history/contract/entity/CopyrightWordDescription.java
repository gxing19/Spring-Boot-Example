package com.qizhidao.ecloud.history.contract.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CopyrightWordDescription {
    /**
     *   表主键
     */
    private Long wordDescId;

    /**
     *   ips_cr_copyright_t表主键
     */
    private Long copyrightId;

    /**
     *   权利取得方式(1=原始,2=继受,3承受,4=其他)
     */
    private String rightObtainMode;

    /**
     *   权利归属方式(1=受让,2=承受,3=继承)
     */
    private String rightOwnerMode;

    /**
     *   说明
     */
    private String rightOwnerDesc;

    /**
     *   权利拥有状况标识(Y=全部,N=部分)
     */
    private String rightBelongWay;

    /**
     *   范围lookup配置
     */
    private String rightScope;

    /**
     *   权利范围说明
     */
    private Integer rightScopeDesc;

    /**
     *   保留作品样本：0电子介质，1纸介质
     */
    private String sampleType;

    /**
     *   作品保留样本类型
     */
    private String sampleTypeDetail;

    /**
     *   保留样本纸介质 件数
     */
    private Integer sampleCount;

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
}