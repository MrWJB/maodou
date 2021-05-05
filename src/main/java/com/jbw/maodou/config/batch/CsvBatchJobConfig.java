package com.jbw.maodou.config.batch;

import com.jbw.maodou.batch.User;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class CsvBatchJobConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    DataSource dataSource;

    /**
     * spring batch 提供了一些常用的ItemReader：
     *  JdbcPagingItemReader用来读取数据库中的数据。
     *  StaxEventItemReader用来读取xml数据。
     *  FlatFileItemReader则是一个加载普通文件的ItemReader。
     * @return
     */
    @Bean
    @StepScope
    FlatFileItemReader<User> itemReader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1); //跳过第一行
        reader.setResource(new ClassPathResource("data.csv")); //配置数据文件的位置
        //通过setLineMapper方法设置每一行的数据信息。setNames方法配置了data.csv文件一种有4列。setDelimiter设置分隔符。
        reader.setLineMapper(new DefaultLineMapper<User>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames("id","username","address","gender");
                setDelimiter("\t");
            }});
            //设置要映射的实体属性。
            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){{
                setTargetType(User.class);
            }});
        }});
        return reader;
    }

    /**
     * 数据写出逻辑
     * @return
     */
    @Bean
    JdbcBatchItemWriter jdbcBatchItemWriter() {
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into user(id,username,address,gender) " +
                "values(:id,:username,:address,:gender)");
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }
    @Bean
    Step csvStep() {
        return stepBuilderFactory.get("csvStep")
                //每读取到2行数据就执行一次write操作。
                .<User, User>chunk(2)
                .reader(itemReader())
                .writer(jdbcBatchItemWriter())
                .build();
    }

    /**
     * job的名字是csvJob
     * @return
     */
    @Bean
    Job csvJob() {
        return jobBuilderFactory.get("csvJob")
                .start(csvStep())
                .build();
    }
}
