package com.didispace.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Hello world!
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController   //路由对应的方法返回的是字符串（spring会 把方法返回的转成字符串）

public class App {
@RequestMapping("/test")
String home(HttpServletRequest request,HttpSession session) {
	
Object sessionBrowser = session.getAttribute("browser");

if (sessionBrowser == null) {

    System.out.println("不存在session，设置browser=" + "xiaoben");

    session.setAttribute("browser", "xiaoben");

} else {

    System.out.println("存在session，browser=" + sessionBrowser.toString());

}

Cookie[] cookies = request.getCookies();

if (cookies != null && cookies.length > 0) {

    for (Cookie cookie : cookies) {

        System.out.println(cookie.getName() + " : " + cookie.getValue());

    }

}
	
return "Hello Worldw!";
}

@RequestMapping("/bean")
public Object  bean(HttpServletRequest request,HttpSession session) {
return  new bean().getBean("testDemo");
}

}

