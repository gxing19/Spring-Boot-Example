package com.gxitsky.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 状态记录表
 */
@Data
@Accessors(chain = true)
@Entity(name = "take_leave_state_record")
public class TakeLeaveStateRecord {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    /**
     * 关联ID
     */
    private Integer takeLeaveId;
    /**
     * 状态
     */
    private String state;
    /**
     * 审批人ID
     */
    private String auditId;
    /**
     * 审批人姓名
     */
    private String auditor;
    /**
     * 审批时间
     */
    private Date auditDate;
}
