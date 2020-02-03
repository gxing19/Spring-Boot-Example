package com.tx.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Accessors(chain = true)
@Table("t_score")
public class Score {

    @Id
    private Long id;
    private Long userId;
    private Integer score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Score() {
    }

    public Score(Long userId, Integer score, Date createTime) {
        this.userId = userId;
        this.score = score;
        this.createTime = createTime;
    }
}
