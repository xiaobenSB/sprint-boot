package com.didispace.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller   //路由对应的方法返回的是静态目录路径（spring会 把方法返回的转成静态目录路径）
public class thymeleafModel {
	
	public class User {
	    public User(String string, String string2, String string3, Date date) {
			this.id = string;
			this.username = string2;
			this.password = string3;
			this.createTime = date;
		}
	    public String id;
	    public String username;
	    public String password;
	    public Date createTime;
	    //省略get/set方法..
	}
	
	 private List<User> userList = new ArrayList<User>();
	    {
	        userList.add(new User("1", "socks", "123456", new Date()));
	        userList.add(new User("2", "admin", "111111", new Date()));
	        userList.add(new User("3", "jacks", "222222",  new Date()));
	        System.out.println(new User("1222222", "socks", "123456", new Date()).id);
	    }
     

	
	@RequestMapping("/index.html")
	public String index(Model model) {
	
		//List == [] == new ArrayList(), object == map == {} == String == new HashMa(), resultList.add == [].push
		/*
		 *    List a = new ArrayList();
			  Map b = new HashMap();
			  Object c = b;
			  Object aa = "www";
		      System.out.println(aa);	
			  System.out.println(a);
			  System.out.println(b);
			  System.out.println(c); 
		 * */
		/*    List<Map<String, Object>> resultList
		   *          上面是设置resultList变量存储的数据必须是List类型，添加的参数类型必须是<Map<String, Object>>
		 * */
		 List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();    //[{string,Object}]类似
	        Map<String, Object> student = new HashMap<String, Object>(){{
	            put("sid", "101");
	            put("sname", "张三");
	            put("sage", "20");
	            put("scourse", new HashMap<String, String>(){{
	                put("cname", "语文,数学,英语");
	                put("cscore", "93,95,98");
	            }});
	        }};
	        resultList.add(student);
	        student = new HashMap<String, Object>(){{
	            put("sid", "102");
	            put("sname", "李四");
	            put("sage", "30");
	            put("scourse", new HashMap<String, String>(){{  
	                put("cname", "物理,化学,生物");
	                put("cscore", "92,93,97");
	            }});
	        }};
	    resultList.add(student);
	    
	    List<Map<String,String>> array = new ArrayList<Map<String,String>>();
	    array.add( new HashMap<String, String>(){{ put("name","xiaoben");  }});
	    
	    model.addAttribute("resultList", resultList);
	    model.addAttribute("userList", userList);
	    model.addAttribute("array", array);
		model.addAttribute("hello","Hello, Spring Bootwww!");
		return "one/index";
	}
	
	@RequestMapping("/404.html")
	public String error(Model model) {
		return "one/404";
	}
}
