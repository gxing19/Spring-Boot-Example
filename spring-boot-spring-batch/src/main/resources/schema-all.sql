DROP TABLE IF EXISTS person;

CREATE TABLE `person` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) DEFAULT NULL,
  `last_name` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


-- Spring Batch 启动必须初始化的元数据(记录job和step),默认是嵌入的内部数据库,
-- 当使用mysql时,初始化无数据必须配置 spring.batch.initialize-schema=always ,
-- 当 Spring Batch 提供的元数据的初始化 SQL 没有判断表是否存在,当应用重启时会报表已存在的错误,以下增加表已存在时删除的操作
-- 或者 spring.batch.initialize-schema=always ,第一次使用 always, 下次启动时改为 never 。
drop table if exists batch_job_seq;
drop table if exists batch_job_execution_seq;
drop table if exists batch_job_execution_params;
drop table if exists batch_job_execution_context;
drop table if exists batch_step_execution_context;
drop table if exists batch_step_execution_seq;
drop table if exists batch_step_execution;
drop table if exists batch_job_execution;
drop table if exists batch_job_instance;
