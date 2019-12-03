/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.service;

import com.github.pagehelper.PageInfo;
import com.qizhidao.ecloud.framework.common.http.ResponseVO;
import com.qizhidao.ecloud.framework.common.user.CurrentUser;
import com.qizhidao.ecloud.history.contract.entity.ContractDetailDataVO;
import com.qizhidao.ecloud.history.contract.entity.transition.TransitionCommonTodo;
import com.qizhidao.ecloud.history.contract.excel.model.ContractExcelData;
import com.qizhidao.ecloud.history.contract.param.ContractFilterParam;
import com.qizhidao.ecloud.history.contract.result.ContractBaseInfoResult;
import com.qizhidao.ecloud.history.contract.vo.ContractInfoVO;
import com.qizhidao.ecloud.history.contract.vo.ContractSummary;
import com.qizhidao.ecloud.history.contract.vo.base.ContractBaseInfoVO;
import com.qizhidao.ecloud.history.contract.vo.remote.ServiceProductVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * ClassName: com.qizhidao.ecloud.history.contract.service.IContractService
 *
 * Description: ${DESCRIPTION}
 *
 * Date: 11/21/18
 *
 * @author James
 * @version 1.0.0
 */
public interface IContractService/* extends IService<Contract>*/ {

    public static final String USERMAP_KEY_IDS = "systemUserIds";
    public static final String USERMAP_KEY_NAMES = "systemUserNames";
    public static final String USERMAP_KEY_OTHERS = "otherUsers";
	
    /**
     * 合同列表分页接口
     */
    List<ContractSummary> listContractSummaryByPage(ContractFilterParam param, PageInfo<ContractSummary> page);

	List<ContractSummary> listContractSummaryByPageV1(ContractFilterParam param, PageInfo<ContractSummary> page);
	
	ContractSummary getReceivableAmountByContractId(Long contractId);
	
    void removeAllByContractId(Long id);

    void saveOrUpdate(ContractInfoVO contractInfoVO);

    /**
     * 案件详情页的合同头部信息
     */
    ContractBaseInfoVO getContractBaseInfoById(Long contractId);
    
    

    /**
     * 合同详情获取
     */
    ContractInfoVO selectById(Long contractId);

    /**
     * 根据合同编号获取客户主键
     */
    Long getCustomerIdByContractNo(String sysContractNo);

    /**
     * 根据线下合同编号模糊查询
     */
    List<ContractBaseInfoVO> listBaseInfoByContractNo(String contractNo);

    /**
     * 生成系统案件编号
     */
    String getSysCaseCode(String officeCode, String businessLineCode, String businessClassifyCode,
                          String productTypeCode, String caseChannelCode, String aliasCode);

    /**
     * excel数据导入
     */
    void saveExcelData(CurrentUser user, MultipartFile file);

    /**
     * 合同报表导出,合同页签的查询
     *
     * @param param 界面查询条件
     * @return List<ContractExportVO>
     */
    List<ContractExcelData> findContractExportList(ContractFilterParam param);

    /**
     * 通过线下合同编号获取合同的基本信息
     */
    ContractBaseInfoResult getContractBaseInfoByContractNo(String publicNo);

    /**
     * 通过线下合同编号集合获取合同的基本信息 线下合同编号为key
     */
    Map<String, ContractBaseInfoResult> getContractBaseInfoByContractNoList(List<String> contractNoList);

    /**
     * 检查合同是否存在关联的案件
     */
    int checkCaseByContractId(Long id);

    /**
     * 小工具处理合同附件相关信息
     * @param file
     * @return
     */
    ResponseVO importBusinessFile(MultipartFile file);

    /**
     * 校验合同号是否存在
     * @param contractNo
     * @return
     */
    ResponseVO checkContractNo(String contractNo);
    
    /**
     * 从合同立案和商城订单中查询合同信息
     * 
     * @Description: TODO  
     * @param: @param contractNo
     * @param: @param orderNo
     * @param: @param caseSrc
     * @param: @return      
     * @return: Contract   
     * @time： 2019年6月15日 上午10:35:33   
     * @throws
     */
    ContractDetailDataVO queryContractDetail(TransitionCommonTodo headTodo);
    
    /**
     * 
     * 
     * @Description: TODO  
     * @param: @param contractCode
     * @param: @param businessCode
     * @param: @param orderNo
     * @param: @param caseSrc
     * @param: @return      
     * @return: ServiceProductVO   
     * @time： 2019年6月24日 下午5:15:11   
     * @throws
     */
    ServiceProductVO queryServiceProduct(TransitionCommonTodo headTodo);
    
    /**
     * @Description: TODO  
     * @param: @param contractCode
     * @param: @param businessCode
     * @param: @param orderNo
     * @param: @param caseSrc
     * @param: @return      
     * @return: ContractBaseInfoVO   
     * @time： 2019年6月24日 下午5:15:05   
     * @throws
     */
    ContractBaseInfoVO buildContractInfoFromTodoHead(TransitionCommonTodo headTodo);
    /**
     * 查询合同信息
     * @param contractNo
     * @return
     */
    ContractBaseInfoVO queryByContractNo(String contractNo);

    /**
     * 按合同编码关联更新商标、版权、专利、知产运营的客户名称和编码、合同编码、办事处编码
     * @param contractNo
     */
    void updateCasedTableBycontractNo(String contractNo);
    
    Map<String,String> getUserIds(String systemUserNames);


    void exportContractList(ContractFilterParam param);
    
}
