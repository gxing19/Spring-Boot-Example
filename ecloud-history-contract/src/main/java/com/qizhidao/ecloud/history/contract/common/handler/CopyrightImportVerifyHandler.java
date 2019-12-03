package com.qizhidao.ecloud.history.contract.common.handler;

import com.qizhidao.ecloud.history.contract.component.SpringBootStartInitData;
import com.qizhidao.ecloud.history.contract.entity.LookupItem;
import com.qizhidao.ecloud.history.contract.model.doo.*;
import com.qizhidao.ecloud.history.contract.model.result.ContractBaseInfoResult;
import com.qizhidao.ecloud.history.contract.model.vo.LookupItemVO;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Data
@Accessors(chain = true)
@Component
public class CopyrightImportVerifyHandler {


    private SpringBootStartInitData bootStartInitData;

    public CopyrightImportVerifyHandler(SpringBootStartInitData bootStartInitData) {
        this.bootStartInitData = bootStartInitData;
    }

    //合同基本信息
    private Map<String, ContractBaseInfoResult> contractBaseInfoResultMap;
    //ips 状态
    private List<LookupItem> ipsStatusList;
    /*业务分类*/
    public Map<String, ServiceBranchClassDO> businessMapCopyright = bootStartInitData.getBusinessMapCopyright();
    /*产品分类*/
    public Map<String, ServiceProjectClassDO> projectMapCopyright = bootStartInitData.getProjectMapCopyright();
    /*案件途径*/
    public Map<String, ServiceCaseModeDO> caseMapCopyright = bootStartInitData.getCaseMapCopyright();
    //国
    private List<CountryDO> countryList = bootStartInitData.getCountryList();
    //省
    private List<CityDO> provinceList = bootStartInitData.getProvinceList();
    //市
    private List<CityDO> cityList = bootStartInitData.getCityList();
    //著作权人类型
    private List<LookupItem> manTypeList = bootStartInitData.getManTypeList();
    //身份类型
    private List<LookupItem> idTypeList = bootStartInitData.getIdTypeList();
    //代理人
    private List<LookupItemVO> agentUserList = bootStartInitData.getAgentUserList();


    /**
     * 校验数据
     *
     * @param importTotalVOS
     * @param sheetName
     * @param contractBaseInfoResultMap
     * @param ipsStatusList
     * @return
     */
    /*public VerifyHandlerResult verifyData(List<CopyrightImportTotalVO> importTotalVOS, String sheetName,
                                          Map<String, ContractBaseInfoResult> contractBaseInfoResultMap, List<LookupItem> ipsStatusList) {
        this.contractBaseInfoResultMap = contractBaseInfoResultMap;
        this.ipsStatusList = ipsStatusList;

        VerifyHandlerResult verifyResult = new VerifyHandlerResult();

        List<CopyrightImportTotalVO> successList = new ArrayList<>();
        List<CopyrightImportTotalVO> failList = new ArrayList<>();


        for (CopyrightImportTotalVO importTotalVO : importTotalVOS) {
            boolean result = verifyByCopyrightType(importTotalVO, sheetName);
            if (result) {
                //校验成功数据
                successList.add(importTotalVO);
            } else {
                //校验失败数据
                failList.add(importTotalVO);
            }
        }

        if (successList.size() > 0) {
            verifyResult.setSuccessList(successList);
        }

        if (failList.size() > 0) {
            verifyResult.setFailList(failList);
        }
        return verifyResult;
    }*/


    /*public boolean verifyByCopyrightType(CopyrightImportTotalVO importTotalVO, String sheetName) {

        switch (sheetName) {
            case "软著登记":
                return verifyCopyrightReg(importTotalVO);
            case "":
                break;
        }
        return true;
    }*/

    /*private boolean verifyCopyrightReg(CopyrightImportTotalVO importTotalVO) {
        log.info("开始校验导入数据");
        VerifyHandlerResult result = new VerifyHandlerResult();

        StringBuffer errorMsg = new StringBuffer();

        //校验合同信息
        this.verifyContract(importTotalVO, errorMsg);
        //校验线下案件编号
        this.verifyCopyrightNo(importTotalVO, errorMsg);
        //校验业务分类
        this.verifyBusinessClass(importTotalVO, errorMsg);
        //校验产品分类
        this.verifyProductType(importTotalVO, errorMsg);
        //校验案件途径
        this.verifyCaseChannle(importTotalVO, errorMsg);
        //校验IPS状态
        this.verifyIpsStatus(importTotalVO, errorMsg);
        //校验发表地点(国家)
        this.verifyPublishCounty(importTotalVO, errorMsg);
        //校验著作权人类型
        this.verifyManType(importTotalVO, errorMsg);
        //著作权人归属性：国、省不需要级联校验
        //校验著作权人归属地：国
        this.verifyCopyrightCounty(importTotalVO, errorMsg);
        //校验著作权人归属地：省
        this.verifyCopyrightProvince(importTotalVO, errorMsg);
        //校验身份证类型
        this.verifyIdType(importTotalVO, errorMsg);
        //校验代理人姓名/公司
        this.verifyAgent(importTotalVO, errorMsg);

        log.info("校验导入数据结束,错误信息:{}", errorMsg.toString());

        if (errorMsg.length() > 0) {
            return false;
        }
        return true;
    }*/

    /**
     * 校验代理人名称
     *
     * @param importTotalVO
     * @param errorMsg
     *//*
    private void verifyAgent(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isNotEmpty(importTotalVO.getAgentUserName())) {
            for (LookupItemVO agentUser : agentUserList) {
                if (agentUser.getItemName().equals(importTotalVO.getAgentUserName())) {
                    importTotalVO.setAgentUserId(agentUser.getItemId());
                    break;
                }
            }
            if (null == importTotalVO.getAgentUserId()) {
                errorMsg.append("代理人姓名或单位在系统中不存在#");
            }
        } else {
            errorMsg.append("代理人姓名或单位不能为空#");
        }
    }*/

    /**
     * 校验身份证类型
     */
    /*private void verifyIdType(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isNotEmpty(importTotalVO.getIdTypeName())) {
            for (LookupItem idType : idTypeList) {
                if (idType.getItemName().equals(importTotalVO.getIdTypeName())) {
                    importTotalVO.setIdType(Integer.valueOf(idType.getItemCode()));
                    break;
                }
            }
            if (null == importTotalVO.getIdType()) {
                errorMsg.append("选择的身份类型系统中不存在#");
            }
        } else {
            errorMsg.append("请选择身份类型#");
        }
    }*/

    /**
     * 校验著作权人归属地：省份
     *
     * @param importTotalVO
     * @param errorMsg
     */
    /*private void verifyCopyrightProvince(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isNotEmpty(importTotalVO.getProvinceName())) {
            for (CityDO province : provinceList) {
                if (province.getShortname().equals(importTotalVO.getProvinceName())) {
                    importTotalVO.setProvinceId(province.getId());
                    break;
                }
            }
            if (null == importTotalVO.getProvinceId()) {
                errorMsg.append("选择的著作权人省份系统中不存在#");
            }
        } else {
            errorMsg.append("请选择著作权人省份#");
        }
    }*/

    /**
     * 校验著作权人归属地：国家
     *
     * @param importTotalVO
     * @param errorMsg
     */
    /*private void verifyCopyrightCounty(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {

        //校验国家
        if (StringUtils.isNotEmpty(importTotalVO.getCountryName())) {
            for (CountryDO countryDO : countryList) {
                if (countryDO.getCountyName().equals(importTotalVO.getCountryName())) {
                    importTotalVO.setCountryId(countryDO.getId().longValue());
                    break;
                }
            }
            if (null == importTotalVO.getCountryId()) {
                errorMsg.append("选择的著作权人国籍系统中不存在#");
            }
        } else {
            errorMsg.append("请选择著作权人国籍#");
        }
    }*/

    /**
     * 校验著作权人类型
     *
     * @param importTotalVO
     * @param errorMsg
     */
    /*private void verifyManType(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isNotEmpty(importTotalVO.getCopyrightTypeName())) {
            for (LookupItem manType : manTypeList) {
                if (manType.getItemName().equals(importTotalVO.getCopyrightTypeName())) {
                    importTotalVO.setCopyrightType(Integer.valueOf(manType.getItemCode()));
                    break;
                }
            }
            if (null == importTotalVO.getCopyrightType()) {
                errorMsg.append("选择的著作权人类型系统中不存在#");
            }

        } else {
            errorMsg.append("请选择著作权人类型#");
        }
    }*/

    /**
     * 校验发表地点(国家)
     *
     * @param importTotalVO
     * @param errorMsg
     */
    /*private void verifyPublishCounty(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isNotEmpty(importTotalVO.getPublishCountry())) {
            for (CountryDO countryDO : countryList) {
                if (countryDO.getCountyName().equals(importTotalVO.getPublishCountry())) {
                    importTotalVO.setPublishPlaceId(countryDO.getId().longValue());
                    break;
                }
            }
            if (null == importTotalVO.getPublishPlaceId()) {
                errorMsg.append("选择的首次发表地点(国家)系统中不存在#");
            }
        } else {
            errorMsg.append("请选择首次发表地点(国家)#");
        }
    }*/


    /**
     * 校验IPS状态
     *
     * @param importTotalVO
     * @param errorMsg
     */
    /*private StringBuffer verifyIpsStatus(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isNotEmpty(importTotalVO.getIpsStatusName())) {
            for (LookupItem lookupItem : ipsStatusList) {
                if (lookupItem.getItemName().equals(importTotalVO.getIpsStatusName())) {
                    importTotalVO.setStatus(Integer.parseInt(lookupItem.getItemCode()));
                    break;
                }
            }
            if (null == importTotalVO.getStatus()) {
                errorMsg.append("IPS 状态名称系统中不存在#");
            }
        } else {
            errorMsg.append("IPS 状态不能为空#");
        }
        return errorMsg;
    }*/

    /**
     * 校验产品分类
     *
     * @param importTotalVO
     * @param errorMsg
     */
    /*private void verifyProductType(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isNotEmpty(importTotalVO.getProductTypeName())) {
            Set<String> keySet = projectMapCopyright.keySet();
            for (String key : keySet) {
                ServiceProjectClassDO serviceProjectClassDO = projectMapCopyright.get(key);
                if (serviceProjectClassDO.getBusinessProjectName().equals(importTotalVO.getProductTypeName())) {
                    int id = serviceProjectClassDO.getId().intValue();
                    importTotalVO.setProductTypeId(id);
                    break;
                }
            }
            if (null == importTotalVO.getProductTypeId()) {
                errorMsg.append("产品分类系统中不存在#");
            }
        } else {
            errorMsg.append("产品分类不能为空#");
        }
    }*/

    /**
     * 校验业务分类
     *
     * @param importTotalVO
     * @param errorMsg
     * @return
     */
    /*private StringBuffer verifyBusinessClass(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        Set<String> keySet = businessMapCopyright.keySet();
        if (StringUtils.isNotEmpty(importTotalVO.getBusinessClassifyName())) {
            for (String key : keySet) {
                ServiceBranchClassDO serviceBranchClassDO = businessMapCopyright.get(key);
                String businessBranchName = serviceBranchClassDO.getBusinessBranchName();
                if (businessBranchName.equals(importTotalVO.getBusinessClassifyName())) {
                    int id = serviceBranchClassDO.getId().intValue();
                    importTotalVO.setBusinessClassifyId(id);
                    break;
                }
            }
            if (importTotalVO.getBusinessClassifyId() == null) {
                errorMsg.append("业务分类名称系统中不存在#");
            }
        } else {
            errorMsg.append("业务分类名称不能为空#");
        }
        return errorMsg;
    }*/

    /**
     * 校验案件途径
     *
     * @param importTotalVO
     * @param errorMsg
     * @return
     */
    /*private StringBuffer verifyCaseChannle(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        Set<String> stringSet = caseMapCopyright.keySet();
        if (StringUtils.isNotEmpty(importTotalVO.getCaseChannelName())) {
            for (String key : stringSet) {
                ServiceCaseModeDO serviceCaseModeDO = caseMapCopyright.get(key);
                if (serviceCaseModeDO.getBusinessCaseName().equals(importTotalVO.getCaseChannelName())) {
                    int id = serviceCaseModeDO.getId().intValue();
                    importTotalVO.setCaseChannelId(id);
                    break;
                }
            }
            if (importTotalVO.getCaseChannelId() == null) {
                errorMsg.append("案件途径名称系统中不存在#");
            }
        } else {
            errorMsg.append("案件途径不能为空#");
        }
        return errorMsg;
    }*/

    /**
     * 校验线下案件编号
     *
     * @param importTotalVO
     * @param errorMsg
     */
    /*private void verifyCopyrightNo(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        if (StringUtils.isEmpty(importTotalVO.getCopyrightNo())) {
            errorMsg.append("线下案件编号不能为空#");
        }
    }*/

    /**
     * 校验合同信息
     *
     * @param importTotalVO
     * @param errorMsg
     * @return
     */
    /*private StringBuffer verifyContract(CopyrightImportTotalVO importTotalVO, StringBuffer errorMsg) {
        //校验线下合同编号
        String contractNo = importTotalVO.getContractNo();
        if (StringUtils.isNotEmpty(contractNo)) {
            if (contractBaseInfoResultMap.containsKey(contractNo)) {
                ContractBaseInfoResult contractBaseInfoResult = contractBaseInfoResultMap.get(contractNo);
                importTotalVO.setContractId(contractBaseInfoResult.getContractId())
                        .setContractCode(contractBaseInfoResult.getContractNo())
                        .setOfficeCode(contractBaseInfoResult.getOfficeCode())
                        .setCustomerName(contractBaseInfoResult.getCustomerName())
                        .setCustomerCode(contractBaseInfoResult.getCustomerCode());
            } else {
                errorMsg.append("线下合同系统中不存在#");
            }
        } else {
            errorMsg.append("线下合同编号不能为空#");
        }
        return errorMsg;
    }*/


}
