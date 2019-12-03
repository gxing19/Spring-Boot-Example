/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.entity.base;

import com.alibaba.fastjson.JSON;
import com.qizhidao.ecloud.framework.common.entity.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ClassName: com.qizhidao.ecloud.base.entity.BaseResourceDO
 * <p>
 * Description:基础类
 * <p>
 * Date: 2018/12/25/025 - 14:21
 *
 * @author shun.li
 * @version 1.0.0
 */
@ApiModel(value = "BaseResourceDO", description = "基础实体参数")
public class BaseResourceDO extends BaseDO implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "业务主键")
    private Long id;
    
    @ApiModelProperty(value = "排序")
    private Long sort;
    
    @ApiModelProperty(value = "删除状态：0未删除，1已删除")
    private Integer deleteStatus;
    
    @ApiModelProperty(value = "状态：0启用，1禁用")
    private Integer status;
    
    @ApiModelProperty(value = "创建人名称")
    private String createdByName;
    
    @ApiModelProperty(value = "最后修改人名称")
    private String lastUpdatedByName;
   
    

    @ApiModelProperty(value = "存储组织关系")
    private String treeCode;
   

    @ApiModelProperty(value = "搜索关键字")
    private String searchKey;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}

	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
