package com.didispace.model.spingBatchLearn.file_mysql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class fileMysqlJobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(fileMysqlJobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public fileMysqlJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		System.out.println("fileMysqlJobCompletionNotificationListener被配置了");
		this.jdbcTemplate = jdbcTemplate;
	}
	
	long startTime;
    long endTime;
	@Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        System.out.println("任务处理开始。依次执行 ——> 数据读取 ——> 数据处理 ——> 数据写入");
    }

	@Override
	public void afterJob(JobExecution jobExecution) {
		 System.out.println("任务处理结束");
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			/*jdbcTemplate.query("SELECT first_name, last_name FROM people",
				(rs, row) -> new fileMysqlPerson(
					rs.getString(1),
					rs.getString(2))
			).forEach(person -> log.info("Found <" + person + "> in the database."));*/
		}
	}
	
}
