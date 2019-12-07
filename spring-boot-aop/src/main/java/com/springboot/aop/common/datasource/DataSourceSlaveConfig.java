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
 * @name: DataSourceslaveConfig
 * @desc: slave 数据源
 **/
@Configuration
@MapperScan(basePackages = "com.springboot.aop.mapper.slave", sqlSessionTemplateRef = "sqlSessionTemplateSlave", markerInterface = BaseMapper.class)
public class DataSourceSlaveConfig {

    /**
     * slave 数据源
     * @return DataSource
     */
    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dataSourceSlave() {
        //一定要用DruidDataSource类，不然的话druid数据库连接池是不会起作用的
//        DruidDataSource dataSourceOne = new DruidDataSource();
        DruidDataSource dataSourceOne = DruidDataSourceBuilder.create().build();
        return dataSourceOne;
    }

    /**
     * slave Mybatis Configuration
     * @return Configuration
     */
    @Bean(name = "configurationSlave")
    public org.apache.ibatis.session.Configuration configurationSlave() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //开启驼峰映射
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    /**
     * slave SqlSessionFactory
     * @param dataSource
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactorySlave")
    public SqlSessionFactory sqlSessionFactorySlave(@Qualifier("dataSourceSlave") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.springboot.aop.entity");
        sqlSessionFactoryBean.setConfiguration(configurationSlave());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * slave SqlSessionTemplate
     * @param sqlSessionFactory
     * @return SqlSessionTemplate
     */
    @Bean(name = "sqlSessionTemplateSlave")
    public SqlSessionTemplate sqlSessionTemplateSlave(@Qualifier("sqlSessionFactorySlave") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * slave TransactionManager
     * @param dataSource
     * @return DataSourceTransactionManager
     */
    @Bean(name = "dataSourceTransactionManagerSlave")
    public DataSourceTransactionManager dataSourceTransactionManagerSlave(@Qualifier("dataSourceSlave") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}