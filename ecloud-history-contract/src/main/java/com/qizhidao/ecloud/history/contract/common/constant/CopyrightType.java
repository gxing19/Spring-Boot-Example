package com.qizhidao.ecloud.history.contract.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CopyrightType {

    COPYRIGHT_REG("软著登记", "CR011"),
    COPYRIGHT_QUERY("软著查询","CR014"),
    COPYRIGHT_CHANGE("软著变更","CR013"),
    COPYRIGHT_TRANSFER("软著转让/许可","CR012"),
    COPYRIGHT_REPLACE_CERT("软著补、换证","CR017"),
    COPYRIGHT_REVOKE("软著撤销","CR015"),
    COPYRIGHT_SOFTWARE_TEST("软件评测","CR018"),
    COPYRIGHT_WORK_REG("作品版权登记","CR021"),
    COPYRIGHT_WORK_QUERY("作品版权查询","CR027"),
    COPYRIGHT_WORK_CHANGE("作品版权变更","CR022"),
    COPYRIGHT_WORK_TRANSFER("作品版权转让/许可","CR023"),
    COPYRIGHT_WORK_REVOKE("作品版权撤销","CR025"),
    COPYRIGHT_WORK_REPLACE_CERT("作品版权补、换证","CR024");

    private String type;
    private String code;
}
