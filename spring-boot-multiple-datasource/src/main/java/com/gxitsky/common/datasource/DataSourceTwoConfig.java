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
@MapperScan(basePackages = "com.springboot.template.mapper.source2",
        sqlSessionTemplateRef = "sqlSessionTemplateTwo", markerInterface = BaseMapper.class)
public class DataSourceTwoConfig {

    /**
     * @desc: 数据源2
     * @author: gxing
     * @date: 2019/2/15 16:46
     * @param: []
     * @return: javax.sql.DataSource
     **/
    @Bean(name = "dataSourceTwo")
    @ConfigurationProperties(prefix = "spring.datasource.source2")
    public DataSource dataSourceTwo() {
        //一定要用DruidDataSource类，不然的话druid数据库连接池是不会起作用的
//        DruidDataSource dataSourceTwo = new DruidDataSource();
        DruidDataSource dataSourceTwo = DruidDataSourceBuilder.create().build();
        return dataSourceTwo;
    }

    /**
     * @desc: sqlSession 配置
     * @author: gxing
     * @date: 2019/2/19 11:55
     * @param: []
     * @return: org.apache.ibatis.session.Configuration
     **/
    @Bean(name = "configurationTwo")
    public org.apache.ibatis.session.Configuration configurationTwo() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //开启驼峰映射
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    /**
     * @desc: SqlSessionFactory 2
     * @author: gxing
     * @date: 2019/2/15 16:52
     * @param: [dataSource]
     * @return: org.apache.ibatis.session.SqlSessionFactory
     **/
    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTwo(@Qualifier("dataSourceTwo") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.springboot.template.entity");
        sqlSessionFactoryBean.setConfiguration(configurationTwo());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * @desc: SqlSessionTemplate
     * @author: gxing
     * @date: 2019/2/15 16:54
     * @param: [sqlSessionFactory]
     * @return: SqlSessionTemplate
     **/
    @Bean(name = "sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplateTwo(@Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * @desc: 数据源2的事务管理
     * @author: gxing
     * @date: 2019/2/15 16:48
     * @param: [dataSource]
     * @return: org.springframework.jdbc.datasource.DataSourceTransactionManager
     **/
    @Bean(name = "dataSourceTransactionManagerTwo")
    public DataSourceTransactionManager dataSourceTransactionManagerTwo(@Qualifier("dataSourceTwo") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}