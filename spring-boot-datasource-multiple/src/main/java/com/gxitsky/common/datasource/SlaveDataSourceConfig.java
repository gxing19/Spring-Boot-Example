package com.gxitsky.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.gxitsky.mapper.base.BaseMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @name: DataSourceTwoConfig
 * @desc: 多数据源配置之数据源2
 * @author: gxing
 * @date: 2019-02-15 15:53
 **/
@Configuration
@MapperScan(basePackages = "com.springboot.template.mapper.slave",
        sqlSessionTemplateRef = "slaveSqlSessionTemplate", markerInterface = BaseMapper.class)
public class SlaveDataSourceConfig {

    /**
     * @desc: 数据源2
     * @author: gxing
     * @date: 2019/2/15 16:46
     * @param: []
     * @return: javax.sql.DataSource
     **/
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        //一定要用DruidDataSource类，不然的话druid数据库连接池是不会起作用的
//        DruidDataSource dataSourceTwo = new DruidDataSource();
        DruidDataSource slaveDataSource = DruidDataSourceBuilder.create().build();
        return slaveDataSource;
    }

    /**
     * @desc: sqlSession 配置
     * @author: gxing
     * @date: 2019/2/19 11:55
     * @param: []
     * @return: org.apache.ibatis.session.Configuration
     **/
    @Bean(name = "slaveConfiguration")
    public org.apache.ibatis.session.Configuration slaveConfiguration() {
        org.apache.ibatis.session.Configuration slaveConfiguration = new org.apache.ibatis.session.Configuration();
        //开启驼峰映射
        slaveConfiguration.setMapUnderscoreToCamelCase(true);
        return slaveConfiguration;
    }

    /**
     * @desc: SqlSessionFactory 2
     * @author: gxing
     * @date: 2019/2/15 16:52
     * @param: [dataSource]
     * @return: org.apache.ibatis.session.SqlSessionFactory
     **/
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.springboot.template.entity");
        sqlSessionFactoryBean.setConfiguration(slaveConfiguration());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * @desc: SqlSessionTemplate
     * @author: gxing
     * @date: 2019/2/15 16:54
     * @param: [sqlSessionFactory]
     * @return: SqlSessionTemplate
     **/
    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * @desc: 数据源2的事务管理
     * @author: gxing
     * @date: 2019/2/15 16:48
     * @param: [dataSource]
     * @return: org.springframework.jdbc.datasource.DataSourceTransactionManager
     **/
    @Bean(name = "slaveDataSourceTransactionManager")
    public DataSourceTransactionManager slaveDataSourceTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}