package com.qizhidao.ecloud.history.contract.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.qizhidao.ecloud.history.contract.common.constant.CopyrightType;
import com.qizhidao.ecloud.history.contract.common.handler.CopyrightImportVerifyHandler;
import com.qizhidao.ecloud.history.contract.component.SpringBootStartInitData;
import com.qizhidao.ecloud.history.contract.entity.CurrentUser;
import com.qizhidao.ecloud.history.contract.model.vo.CopyrightImportTotalVO;
import com.qizhidao.ecloud.history.contract.service.ICopyrightService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 版权表 服务实现类
 * </p>
 *
 * @author JiaDu
 * @since 2018-11-21
 */
@Slf4j
@Service
public class CopyrightServiceImpl implements ICopyrightService {

    private static final Logger logger = LoggerFactory.getLogger(CopyrightServiceImpl.class);

    @Autowired
    private SpringBootStartInitData bootStartInitData;


    /**
     * 导入版权EXCEL数据
     *
     * @param file excel文件
     * @param user 当前用户
     */
    @Override
    public void importCopyright(MultipartFile file, CurrentUser user) {
        logger.info("进入导入数据业务处理:{}", file.getOriginalFilename());

        try {
            //保存导入日志记录

            ExcelReader reader = ExcelUtil.getReader(file.getInputStream(), true);
            List<String> sheetNames = reader.getSheetNames();

            Map<String, List<CopyrightImportTotalVO>> failListMap = new HashMap<>(13);

            for (String sheetName : sheetNames) {
                ExcelReader sheetReader = ExcelUtil.getReader(file.getInputStream(), sheetName, true);
                switch (sheetName) {
                    case "软著登记":
                        List<CopyrightImportTotalVO> importTotalVOList = sheetReader.read(1, 3, CopyrightImportTotalVO.class);
                        if (importTotalVOList.size() > 0) {

                            List<String> contractNoList = new ArrayList<>();
                            importTotalVOList.forEach(eachRow -> {
                                contractNoList.add(eachRow.getContractNo());
                            });

                            CopyrightImportVerifyHandler verifyHandler = new CopyrightImportVerifyHandler(bootStartInitData);
                            String processCode = CopyrightType.COPYRIGHT_REG.getCode();
                            //案件基本信息
                            /*Map<String, ContractBaseInfoResult> contractBaseInfoResultMap = contractService.getContractBaseInfoByContractNoList(contractNoList);
                            //获取软著登记IPS状态
                            List<LookupItem> ipsStatusList = commonService.selectByBusinessClassifyCode(processCode, LookupClassifyEnum.COPYRIGHT_IPS_STATUS.getClassifyCode(), null);
                            //校验数据
                            VerifyHandlerResult verifyHandlerResult = importVerifyHandler.verifyData(importTotalVOList, sheetName, contractBaseInfoResultMap, ipsStatusList);
                            //装校验失败数据
                            failListMap.put(sheetName, verifyHandlerResult.getFailList());
                            //保存检验通过的数据
                            this.saveOrUpdateCopyrightRegData(verifyHandlerResult.getSuccessList(), user.getId(), processCode);*/
                        }
                        break;
                    /*case "作品版权登记":
                        List<CopyrightWorkRegImportVo> workRegImportVos = sheetReader.read(1, 3, CopyrightWorkRegImportVo.class);
                        System.out.println(JSON.toJSONString(workRegImportVos));
                        break;*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("版权 EXCEL 导入读取文件流错误:{}", e.getMessage());
        }

    }

    /**
     * 保存或更新导入软著登记数据
     *
     * @param importTotalVOList
     * @param userId
     * @param processCode
     */
    /*@Transactional
    public void saveOrUpdateCopyrightRegData(List<CopyrightImportTotalVO> importTotalVOList, Long userId, String processCode) {
        long start = System.currentTimeMillis();
        logger.info("开始保存或更新导入数据");

        for (CopyrightImportTotalVO importTotalVO : importTotalVOList) {
            //为每条数据设置案件类型
            importTotalVO.setProcessCode(processCode);

            //保存或更新案件信息
            this.saveOrUpdateCopyright(importTotalVO, userId);
            //保存或更新基础信息
            infoService.saveOrUpdateCopyrightInfo(importTotalVO, userId);
            //保存或更新著作权人信息
            userService.saveOrUpdateCopyrightUser(importTotalVO, userId);
            //保存或更新软件技术特点
            softwareTechnologyService.saveOrUpdateSoftwareTec(importTotalVO, userId);
            //保存或更新案件跟进信息
            copyrightFollowUpInfoService.saveOrUpdateFollowUpInfo(importTotalVO, userId);
            //保存或更新申请人信息
            applicantService.saveOrUpdateApplicant(importTotalVO, userId);
            //保存或更新代理人信息
            agentService.saveOrUpdateAgent(importTotalVO, userId);
        }

        logger.info("保存或更新导入数据结束,耗时:{}", (System.currentTimeMillis() - start));
    }*/

    /**
     * 保存或更新案件信息(案件主表)-软著信息
     *
     * @param userId
     * @param importTotalVO
     */
    /*@Override
    public void saveOrUpdateCopyright(CopyrightImportTotalVO importTotalVO, Long userId) {
        log.info("开始保存或更新案件信息");
        Copyright copyright = new Copyright();
        BeanUtils.copyProperties(importTotalVO, copyright);
        copyright.setCaseName(importTotalVO.getFullName());
        copyright.setCaseDate(importTotalVO.getCaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        // Date 2 LocalDate
        LocalDate caseCommitDate = importTotalVO.getCaseCommitDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        copyright.setFillingCommitDate(caseCommitDate);
        Copyright oldCopyright = copyrightMapper.queryByCopyrightNo(copyright.getCopyrightNo());
        if (!StringUtils.isEmpty(oldCopyright)) {
            importTotalVO.setCopyrightId(oldCopyright.getCopyrightId());
            copyright.setCopyrightId(oldCopyright.getCopyrightId());
            copyright.setLastUpdateBy(userId);
            copyrightMapper.updateByPrimaryKey(copyright);
        } else {
            copyright.setCreateBy(userId);
            copyrightMapper.insert(copyright);
            //标记是新数据
            importTotalVO.setWhetherNew(true);
            importTotalVO.setCopyrightId(copyright.getCopyrightId());
        }
        log.info("保存或更新案件信息结束");
    }*/

}
