package com.didispace.model.spingBatchLearn;


import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.didispace.model.spingBatchLearn.file_mysql.fileMysqlJobCompletionNotificationListener;
import com.didispace.model.spingBatchLearn.file_mysql.fileMysqlPerson;
import com.didispace.model.spingBatchLearn.file_mysql.fileMysqlPersonItemProcessor;
import com.didispace.model.spingBatchLearn.mysql_file.mysqlFileTestUser;
import com.didispace.model.spingBatchLearn.mysql_file.mysqlFileTestUserItemProcessor;
import com.didispace.model.spingBatchLearn.mysql_file.mysqlFileUserRowMapper;



@Configuration
@EnableBatchProcessing

public class SpringBatchConfig {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
	
    // tag::jobstep[]
    @Bean
    public Job importUserJob(fileMysqlJobCompletionNotificationListener listener,Step step1,Step step2) {
    	System.out.println("springBatch 管理任务配置");
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .next(step2)
            .end()
            .build();
    }
      
    /*
     *             任务一 ---------  读取csv文件数据写入到mysql里
	* */
    
 // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<fileMysqlPerson> reader() {
    	System.out.println("springBatch 任务一  数据读取配置");
        return new FlatFileItemReaderBuilder<fileMysqlPerson>()
            .name("personItemReader")
            .resource(new ClassPathResource("sample-data.csv"))
            .delimited()                                //这里的firstName是为了在fileMysqlPerson使用setFirstName保存值
            .names(new String[]{"firstName", "laNa"})   //同时new String[]{"firstName", "laNa"}也规定了读取的csv文件每一行是两个字符串，多或少就保错
            .fieldSetMapper(new BeanWrapperFieldSetMapper<fileMysqlPerson>() {{
                setTargetType(fileMysqlPerson.class);
            }})
            .build();
    }

    @Bean
    public fileMysqlPersonItemProcessor processor() {
    	System.out.println("springBatch 任务一  数据处理配置");
        return new fileMysqlPersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<fileMysqlPerson> writer(DataSource dataSource) {
    	System.out.println("springBatch 任务一  数据写入配置");
        return new JdbcBatchItemWriterBuilder<fileMysqlPerson>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")   //这个的:firstName是为了使用getFirstName去fileMysqlPerson里获取
            .dataSource(dataSource)
            .build();
    }
    // end::readerwriterprocessor[]
    
    @Bean
    public Step step1(JdbcBatchItemWriter<fileMysqlPerson> writer) {
    	System.out.println("springBatch 任务一 (文件到mysql) 操作流程配置");
    	
    	fileMysqlPersonItemProcessor aaa = processor();
        return stepBuilderFactory.get("step1")
            .<fileMysqlPerson, fileMysqlPerson> chunk(10)
            .reader(reader())
            .processor(aaa)
            .writer(writer)
            .build();
    }
    
	 
    /*
     *             任务二 ---------  读取mysql数据写入到文件里
	* */
	
	@Bean
	public JdbcCursorItemReader<mysqlFileTestUser> itemReader(DataSource dataSource){	   
	System.out.println("springBatch 任务二  数据读取配置");
	JdbcCursorItemReader<mysqlFileTestUser> reader = new JdbcCursorItemReader<mysqlFileTestUser>();  //这个好像是规定setRowMapper的参数类型必须得是mysqlFileTestUser
	reader.setDataSource(dataSource);
	reader.setSql("SELECT person_id, first_name, last_name FROM people");
	reader.setRowMapper(new mysqlFileUserRowMapper());
	return reader;
	}
	
	@Bean
	public mysqlFileTestUserItemProcessor itemProcessor() {
	System.out.println("springBatch 任务二  数据处理配置");
	return new mysqlFileTestUserItemProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<mysqlFileTestUser> itemWriter() {
	System.out.println("springBatch 任务二  数据写入配置");
	FlatFileItemWriter<mysqlFileTestUser> itemWriter = new FlatFileItemWriter<>();
	String userHome = System.getProperty("user.home");
	Resource outputResource = new FileSystemResource(userHome + "/output/demo04/test_user.txt");  //C:\Users\Administrator\output\demo04
	//itemWriter.setAppendAllowed(true);
	itemWriter.setEncoding("UTF-8");
	itemWriter.setResource(outputResource);
	itemWriter.setLineAggregator(new DelimitedLineAggregator<mysqlFileTestUser>() {{
	   setDelimiter(",");
	   setFieldExtractor(new BeanWrapperFieldExtractor<mysqlFileTestUser>() {{
	       setNames(new String[] { "id", "firstName", "lastName" });   //这个好像是会使用getId或getFirsetName去mysqlFileTestUser里获取
	   }});
	}});
	return itemWriter;
	}
	
	@Autowired
	@Bean
	public Step step2(JdbcCursorItemReader<mysqlFileTestUser> itemReader, mysqlFileTestUserItemProcessor itemProcessor, FlatFileItemWriter<mysqlFileTestUser> itemWriter) {
	System.out.println("springBatch 任务二 (mysql到文件) 操作流程配置");
	return stepBuilderFactory.get("step2").<mysqlFileTestUser, mysqlFileTestUser> chunk(10)   //可能一个用来读一个用来写
	   .reader(itemReader)
	   .processor(itemProcessor)
	   .writer(itemWriter)
	   .build();
	}
	
	
	
	// end::jobstep[]
}