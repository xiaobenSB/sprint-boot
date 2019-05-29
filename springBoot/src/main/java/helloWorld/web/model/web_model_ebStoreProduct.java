package helloWorld.web.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class web_model_ebStoreProduct {
	 
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	public web_model_ebStoreProduct(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(String str) {   
		return jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", str);
	}
	
	public List<Map<String,Object>> select(String Sentence) {
		
		List<Map<String,Object>> list = jdbcTemplate.queryForList(Sentence);							 
	    return list;
	}
	
	public String delete() {
		return "";
	}
	
	public int update(String str) {
		return jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", str);
	}
	
}
