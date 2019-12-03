package com.qizhidao.ecloud.history.contract.component;


import com.qizhidao.ecloud.history.contract.common.constant.CityLevelTypeEnum;
import com.qizhidao.ecloud.history.contract.common.constant.LookupClassifyEnum;
import com.qizhidao.ecloud.history.contract.entity.LookupItem;
import com.qizhidao.ecloud.history.contract.model.doo.*;
import com.qizhidao.ecloud.history.contract.model.vo.LookupItemVO;
import com.qizhidao.ecloud.history.contract.service.ICommonRemoteService;
import com.qizhidao.ecloud.history.contract.service.ICommonService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Component
public class SpringBootStartInitData implements CommandLineRunner {

    //案件途径
    private Map<String, ServiceCaseModeDO> caseMapCopyright;
    //产品分类
    private Map<String, ServiceProjectClassDO> projectMapCopyright;
    //业务分类
    private Map<String, ServiceBranchClassDO> businessMapCopyright;
    //国
    private List<CountryDO> countryList;
    //省
    private List<CityDO> provinceList;
    //市
    private List<CityDO> cityList;
    //版权人类型
    private List<LookupItem> manTypeList;
    //证件类型
    private List<LookupItem> idTypeList;
    //代理人
    private List<LookupItemVO> agentUserList;
    //作品创作性质
    List<LookupItem> workCreationQualityList;


    @Autowired
    private ICommonService commonService;

    @Autowired
    private ICommonRemoteService commonRemoteService;

    @Override
    public void run(String... args) throws Exception {
        //处理业务分类、产品分类，案件途径;
        if (CollectionUtils.isEmpty(caseMapCopyright)) {
            List<ServiceCaseModeDO> caseList = commonRemoteService.getAllCaseModeInfo("CR");
            if (!CollectionUtils.isEmpty(caseList)) {
                caseMapCopyright = caseList.stream().collect(Collectors.toMap(ServiceCaseModeDO::getBusinessCaseCode, p -> p));
            }
        }
        if (CollectionUtils.isEmpty(projectMapCopyright)) {
            List<ServiceProjectClassDO> projectList = commonRemoteService.getAllProjectClassInfo("CR");
            if (!CollectionUtils.isEmpty(projectList)) {
                projectMapCopyright = projectList.stream().collect(Collectors.toMap(ServiceProjectClassDO::getBusinessProjectCode, p -> p));
            }
        }
        if (CollectionUtils.isEmpty(businessMapCopyright)) {
            List<ServiceBranchClassDO> branchList = commonRemoteService.getAllBusinessBranchInfo("CR");
            if (!CollectionUtils.isEmpty(branchList)) {
                businessMapCopyright = branchList.stream().collect(Collectors.toMap(ServiceBranchClassDO::getBusinessBranchCode, p -> p));
            }
        }

        //国
        countryList = commonRemoteService.findCountryList();
        //省
        provinceList = commonRemoteService.findCityByLevel(CityLevelTypeEnum.province.getLevelType());
        //市
        cityList = commonRemoteService.findCityByLevel(CityLevelTypeEnum.city.getLevelType());
        //版权人类型
        manTypeList = commonService.selectByBusinessClassifyCode(null, LookupClassifyEnum.COPYRIGHT_MAN_TYPE.getClassifyCode(), null);
        //证件类型
        idTypeList = commonService.selectByBusinessClassifyCode(null, LookupClassifyEnum.COPYRIGHT_ID_TYPE.getClassifyCode(), null);
        //代理人
        LookupItemVO agentTypeItem = new LookupItemVO().setClassifyCode(LookupClassifyEnum.COPYRIGHT_AGENT_TYPE.getClassifyCode());
        agentUserList = commonService.findLookupItemByClassify(agentTypeItem);
        //作品创作性质
        workCreationQualityList = commonService.selectByBusinessClassifyCode(null, LookupClassifyEnum.WORK_CREATION_QUALITY.getClassifyCode(), null);


    }
}
