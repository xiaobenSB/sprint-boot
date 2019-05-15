package com.didispace.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller   //路由对应的方法返回的是静态目录路径（spring会 把方法返回的转成静态目录路径）
public class thymeleafModeljdbc {

  
	@Autowired  //叫spring-boot配置JdbcTemplate
	private JdbcTemplate jdbcTemplate;
	@RequestMapping("/jdbc.html")
	public String index(Model model) {
	
		//不能用new thymeleafModel().index() 获取数据库数据，因为JdbcTemplate没经过spring boot 配置是为Null的
		//所以new thymeleafModel().index()时,可能是直接用了java编译的，没有经过spring boot处理过，
		//spring boot可能是当客户端发送请求时，找到路由对应的文件，这时用spring boot的规则去解析该文件，然后java编译的是spring boot处理过的了
		//要知道数据库的连接配置都在application.properties文件，并且你又
		
		String sql = "select * from eb_wechat_user";   
	    List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
	    
	    model.addAttribute("userList", list);

		return "one/1";
	}

	@RequestMapping("/update")
	public String update() {
		String a = "xiaoben";
		String b = "xiaoming";
		String upDaSql = "INSERT INTO eb_store_gerenzhongxinbg (url,path) VALUES ('"+a+"', '"+b+"')";
		jdbcTemplate.update(upDaSql);  
	
		  
		   /* jdbcTemplate.update(new PreparedStatementCreator() {     换种方式来插入
		    	String upDaSql = "INSERT INTO eb_store_gerenzhongxinbg (url,path) VALUES (?, ?)";
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            // 指定主键
		            PreparedStatement preparedStatement = connection.prepareStatement(upDaSql);
		            preparedStatement.setString(1, "一灰灰5");
		            preparedStatement.setString(2, "wiwiiwwiw");
		            return preparedStatement;
		        }
		    });*/
		return "one/update";
		
		
	}
}
