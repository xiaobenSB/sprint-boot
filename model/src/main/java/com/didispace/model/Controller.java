package com.didispace.model;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Controller implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	  /*response.setCharacterEncoding("UTF-8");
          response.setContentType("text/txt; charset=utf-8");

          PrintWriter out = null ;
          out = response.getWriter();
          out.write("123456小本");
          out.flush();
          out.close();*/
    	
    	  //response.sendRedirect("/404.html"); 重定向
          return true;

    }
 

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	System.out.println("当拦截1返回true时,接着处理对应路由里的（我们）设置，处理完后触发这里");
    }
 

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}



