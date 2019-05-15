package com.didispace.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/cookie")

public class cookie {
	private static Cookie cookie;
	
@RequestMapping("/a")
String home(HttpServletResponse response,@CookieValue("login") String login) {
	//(没有就报错)前提是已经创建了或者已经存在cookie了，那么下面这个就直接把对应的key值拿出来了。
	   System.out.println("testCookieValue,login="+login);
    //下面是生成cookie
	cookie = new Cookie("login","true");
	cookie.setMaxAge(60*60);
	cookie.setPath("/cookie");
	response.addCookie(cookie);
    return "cookie 设置成功！!";
}

}


