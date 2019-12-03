package com.qizhidao.ecloud.history.contract.service;

import com.qizhidao.ecloud.history.contract.entity.Copyright;
import com.qizhidao.ecloud.history.contract.entity.CurrentUser;
import com.qizhidao.ecloud.history.contract.model.vo.CopyrightImportTotalVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 版权表 服务类
 * </p>
 *
 * @author JiaDu
 * @since 2018-11-21
 */
public interface ICopyrightService {

    /**
     * 导入版权EXCEL数据
     *
     * @param file excel文件
     * @param user 当前用户
     */
    void importCopyright(MultipartFile file, CurrentUser user) throws IOException;

    /**
     * 保存案件信息
     * @param importTotalVO
     * @param userId
     */
//    void saveOrUpdateCopyright(CopyrightImportTotalVO importTotalVO, Long userId);

}
