package com.didispace.model.spingBatchLearn.mysql_file;

import java.util.Calendar;

import org.springframework.batch.item.ItemProcessor;

public class mysqlFileTestUserItemProcessor implements ItemProcessor<mysqlFileTestUser, mysqlFileTestUser> {
	
    public mysqlFileTestUser process(mysqlFileTestUser testUser) throws Exception {
        System.out.println("任务2的数据处理");
       /* Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(testUser.getBirthday());
        testUser.setAge(cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR));*/
        return testUser;
    }
}