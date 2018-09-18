package com.springboot.springbatch.common.listener;

import com.springboot.springbatch.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @name: JobCompletionNotificationListener
 * @desc: 完成作业通知监听器
 * 继承JobExecutionListenerSupport,或实现 JobExecutionListener接口
 * @author: gxing
 * @date: 2018-09-17 11:51
 **/
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger logger = LogManager.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            logger.info("!!! JOB FINISHED! Time to verify the results");
            Integer count = jdbcTemplate.queryForObject("select count(*) from person", Integer.class);
            logger.info("!!! Found Person Number:{}",count);
        }


    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job Ready OK.......");
    }
}