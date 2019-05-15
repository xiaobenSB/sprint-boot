package com.didispace.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   //路由对应的方法返回的是字符串（spring会 把方法返回的转成字符串）
public class jdbcMysql {
	
    @Autowired  //叫spring-boot配置JdbcTemplate
    private JdbcTemplate jdbcTemplate;
   
   @RequestMapping("/jdbc")
   public List<Map<String, Object>> index() {
	   String sql = "select * from eb_store_product";
       
		List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
	        for (Map<String, Object> map : list) {
	            Set<Entry<String, Object>> entries = map.entrySet( );
	                if(entries != null) {
	                    Iterator<Entry<String, Object>> iterator = entries.iterator( );
	                    while(iterator.hasNext( )) {
	                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
	                    Object key = entry.getKey( );
	                    Object value = entry.getValue();
	                    System.out.println(key+":"+value);
	                }
	            }
	        }
	      return list;
   }

}
