package com.didispace.model.spingBatchLearn.file_mysql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class fileMysqlPersonItemProcessor implements ItemProcessor<fileMysqlPerson, fileMysqlPerson> {  //可能一个用来读一个用来写

    private static final Logger log = LoggerFactory.getLogger(fileMysqlPersonItemProcessor.class);

    public fileMysqlPerson process(final fileMysqlPerson person) throws Exception {
    	
    	System.out.println("任务1的数据处理");
    	
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final fileMysqlPerson transformedPerson = new fileMysqlPerson(person.getFirstName(), person.getLastName());

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}