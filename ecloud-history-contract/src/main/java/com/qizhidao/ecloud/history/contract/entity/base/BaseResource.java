package com.qizhidao.ecloud.history.contract.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseResource implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6684710580979647949L;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CST")
    private LocalDateTime createDate = LocalDateTime.now();

    /**
     * 最后更新人
     */
    private Long lastUpdateBy;

    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CST")
    private LocalDateTime lastUpdateDate = LocalDateTime.now();


    public void withoutDate4Query() {
        this.createDate = null;
        this.lastUpdateDate = null;
    }

}
