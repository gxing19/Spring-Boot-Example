package com.tx.score.entity.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Accessors(chain = true)
@Table("t_event")
public class Event {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 源ID
     */
    private Long sourceId;
    /**
     * 事件类型
     */
    private String type;
    /**
     * 事件过程
     */
    private String process;
    /**
     * 事件内容
     */
    private String content;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Event() {
    }

    public Event(String type, String process, String content) {
        this.type = type;
        this.process = process;
        this.content = content;
    }
}
