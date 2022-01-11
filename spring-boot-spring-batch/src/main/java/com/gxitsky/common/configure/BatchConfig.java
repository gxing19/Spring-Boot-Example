package com.gxitsky.common.configure;

import com.gxitsky.entity.Person;
import com.gxitsky.common.listener.JobCompletionNotificationListener;
import com.gxitsky.job.PersonItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @name: BatchConfig
 * @desc: Spring Batch Config
 * @author: gxing
 * @date: 2018-09-17 10:34
 **/
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * @desc: 从文件中读取源数据
     * @author: gxing
     * @date: 2018/9/17 10:55
     * @param: []
     * @return: org.springframework.batch.item.file.FlatFileItemReader<com.springboot.springbatch.entity.Person>
     **/
    @Bean
    public FlatFileItemReader<Person> reader() {
        // 读取文件
        FlatFileItemReader<Person> itemReader = new FlatFileItemReader<>();
        // 设置文件路径
        itemReader.setResource(new ClassPathResource("person-data.csv"));

        // 数据和领域模型类做对应映射
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);
        lineTokenizer.setNames(new String[]{"firstName", "lastName"});
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        itemReader.setLineMapper(lineMapper);

        /*itemReader.setLineMapper(new DefaultLineMapper<Person>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"firstName","lastName"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
                setTargetType(Person.class);
            }});
        }});*/
        return itemReader;

/*        FlatFileItemReaderBuilder<Person> itemReaderBuilder = new FlatFileItemReaderBuilder<>();
        itemReaderBuilder.name("personItemReader");
        itemReaderBuilder.resource(new ClassPathResource("person-data.csv"));
        itemReaderBuilder.delimited().names(new String[]{"firstName", "lastName"});
        itemReaderBuilder.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }});
        return itemReaderBuilder.build();*/

/*        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("person-data.csv")) //查找文件,并将文件行内容转换为对象
                .delimited()
                .names(new String[]{"firstName", "lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }}).build();*/
    }

/*    @Bean
    public JdbcPagingItemReader<Person> jdbcBatchItemWriter(DataSource dataSource) {
        JdbcPagingItemReader<Person> itemReader = new JdbcPagingItemReader<>();
        itemReader.setDataSource(dataSource);
        itemReader.setFetchSize(100);//每次从数据库读取数据的行数
        itemReader.setQueryProvider(new MySqlPagingQueryProvider() {{
            setSelectClause("SELECT id,first_name,last_name");
            setFromClause("from person");
            setWhereClause("last_name=:lastName");
            setSortKeys(new HashMap<String, Order>() {{
                put("id", Order.ASCENDING);
            }});
        }});
        itemReader.setParameterValues(new HashMap<String, Object>(){{
            put("lastName","DOE");
        }});
        itemReader.setRowMapper(new BeanPropertyRowMapper<>(Person.class));
        return itemReader;
    }*/

    /**
     * @desc: 处理器-处理数据
     * @author: gxing
     * @date: 2018/9/17 10:57
     * @param: []
     * @return: com.springboot.springbatch.job.PersonItemProcessor
     **/
    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    /**
     * @desc: 输出-输出数据,自动配置了DataSource,以参数方式注入
     * @author: gxing
     * @date: 2018/9/17 11:21
     * @param: [dataSource]
     * @return: org.springframework.batch.item.database.JdbcBatchItemWriter<com.springboot.springbatch.entity.Person>
     **/
    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        // 写入到数据库
        JdbcBatchItemWriter<Person> itemWriter = new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>())
                .sql("INSERT INTO person (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .build();
        return itemWriter;
    }

    /**
     * @desc: 定义作业。作业是根据步骤构建的。
     * @author: gxing
     * @date: 2018/9/17 11:25
     * @param: [listener, step1]
     * @return: org.springframework.batch.core.Job
     **/
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    /**
     * @desc: 定义单个Step步骤, 每个步骤涉及读者，处理器和编写者
     * @author: gxing
     * @date: 2018/9/17 11:27
     * @param: [itemWriter]
     * @return: org.springframework.batch.core.Step
     **/
    @Bean
    public Step step1(JdbcBatchItemWriter<Person> itemWriter) {

        //定义一次写入数据量,此处是10条
        //chunk()前辍<Person, Person>表示输入和输出类型,并与ItemReader <Person>和ItemWriter <Person>对齐
        //在使用之前注入 ItemReader、ItemProcessor 和 ItemWriter
        return stepBuilderFactory.get("step1").<Person, Person>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(itemWriter)
                .build();
    }

    /**
     * 持久化Spring Batch元数据
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
/*    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        return jobRepositoryFactoryBean.getObject();
    }*/

    /**
     * @desc: 启动Job
     * @author: gxing
     * @date: 2018/9/18 9:40
     * @param: [dataSource, transactionManager]
     * @return: org.springframework.batch.core.launch.support.SimpleJobLauncher
     **/
/*    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }*/

}