package helloWorld.common.transaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*
 * 
 * create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);      
 * 
 * */

@Component
public class transactionBookingService {
    private final static Logger logger = LoggerFactory.getLogger(transactionBookingService.class);

    private final JdbcTemplate jdbcTemplate;

    public transactionBookingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Transactional
    public void book(String... persons) {
        for (String person : persons) {
        	
            logger.info("Booking " + person + " in a seat...");
            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person); 
                   
           /* try {
            	jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);     
            }catch(Exception e) {
               System.out.println(e.getMessage());
               throw e;  
            }*/
        }
    }

    public List<String> findAllBookings() {
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }
}
