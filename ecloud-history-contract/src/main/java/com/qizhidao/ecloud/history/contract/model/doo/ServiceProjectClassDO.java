/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.model.doo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ClassName: com.qizhidao.ecloud.common.business.ServiceProjectClassDO
 * <p>
 * Description:
 * <p>
 * Date: 2019/2/5/005 - 12:37
 *
 * @author shun.li
 * @version 1.0.0
 */
@ApiModel(value = "ServiceProjectClassDO", description = "产品分类对象")
public class ServiceProjectClassDO   {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    @ApiModelProperty(value = "业务线编码")
    private String businessCode;

    @ApiModelProperty(value = "产品代码")
    private String businessProjectCode;

    @ApiModelProperty(value = "产品名称")
    private String businessProjectName;

    @ApiModelProperty(value = "产品说明")
    private String businessProjectContent;
    
    private String status;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessProjectCode() {
        return businessProjectCode;
    }

    public void setBusinessProjectCode(String businessProjectCode) {
        this.businessProjectCode = businessProjectCode;
    }

    public String getBusinessProjectName() {
        return businessProjectName;
    }

    public void setBusinessProjectName(String businessProjectName) {
        this.businessProjectName = businessProjectName;
    }

    public String getBusinessProjectContent() {
        return businessProjectContent;
    }

    public void setBusinessProjectContent(String businessProjectContent) {
        this.businessProjectContent = businessProjectContent;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
