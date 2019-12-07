package com.springboot.aop.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.springboot.aop.mapper.base.BaseMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @name: DataSourceMasterConfig
 * @desc: master 数据源
 **/
@Configuration
@MapperScan(basePackages = "com.springboot.aop.mapper", sqlSessionTemplateRef = "sqlSessionTemplateMaster", markerInterface = BaseMapper.class)
public class DataSourceMasterConfig {

    /**
     * master 数据源
     * @return DataSource
     */
    @Bean(name = "dataSourceMaster")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource dataSourceMaster() {
        //一定要用DruidDataSource类，不然的话druid数据库连接池是不会起作用的
//        DruidDataSource dataSourceOne = new DruidDataSource();
        DruidDataSource dataSourceOne = DruidDataSourceBuilder.create().build();
        return dataSourceOne;
    }

    /**
     * master Mybatis Configuration
     * @return Configuration
     */
    @Bean(name = "configurationMaster")
    public org.apache.ibatis.session.Configuration configurationMaster() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //开启驼峰映射
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    /**
     * master SqlSessionFactory
     * @param dataSource
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactoryMaster")
    @Primary
    public SqlSessionFactory sqlSessionFactoryMaster(@Qualifier("dataSourceMaster") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.springboot.aop.entity");
        sqlSessionFactoryBean.setConfiguration(configurationMaster());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * master SqlSessionTemplate
     * @param sqlSessionFactory
     * @return SqlSessionTemplate
     */
    @Bean(name = "sqlSessionTemplateMaster")
    @Primary
    public SqlSessionTemplate sqlSessionTemplateMaster(@Qualifier("sqlSessionFactoryMaster") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * master TransactionManager
     * @param dataSource
     * @return DataSourceTransactionManager
     */
    @Bean(name = "dataSourceTransactionManagerMaster")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManagerMaster(@Qualifier("dataSourceMaster") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}