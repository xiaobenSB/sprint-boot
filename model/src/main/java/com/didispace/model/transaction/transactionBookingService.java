package com.didispace.model.transaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*
 * 
 * create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);         //创建所需要的表的脚本
 * 
 * */

@Component
public class transactionBookingService {
    private final static Logger logger = LoggerFactory.getLogger(transactionBookingService.class);

    private final JdbcTemplate jdbcTemplate;

    public transactionBookingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
//数据库引擎要支持事务，如果是MySQL，注意表要使用支持事务的引擎，比如innodb，如果是myisam，事务是不起作用的
    @Transactional
    public void book(String... persons) {
        for (String person : persons) {
        	
            logger.info("Booking " + person + " in a seat...");
            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person); 
                   
           /* try {
            	jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);     
            }catch(Exception e) {
               System.out.println(e.getMessage());
               throw e;  //要抛出错误才会回滚
            }*/
        }
    }

    public List<String> findAllBookings() {
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }
}
