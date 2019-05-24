package com.didispace.model.spingBatchLearn.mysql_file;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class mysqlFileUserRowMapper implements RowMapper<mysqlFileTestUser> {
    @Override
    public mysqlFileTestUser mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	System.out.println("任务2之拿到读取的数据并准备写入到mysqlFileTestUser类里");
    	
    	mysqlFileTestUser user = new mysqlFileTestUser();
        user.setId(rs.getInt("person_id"));
        //System.out.println(rs.getInt("person_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    }
}