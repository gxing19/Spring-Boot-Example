package com.gxitsky.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * 请假单实体
 */
@Data
@ToString
@Entity(name = "take_leave")
public class TakeLeave {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 请假人
     */
    private String username;
    /**
     * 请假开始日期
     */
    private String startDate;
    /**
     * 请假天数
     */
    private Integer leaveDays;

    /**
     * 当前状态
     */
    private String currentState;

    /**
     * 状态记录
     */
    @Transient
    private List<TakeLeaveStateRecord> leaveStateRecords;

}
