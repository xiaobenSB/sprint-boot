package helloWorld.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RouteConfigConfig implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	  /*response.setCharacterEncoding("UTF-8");
          response.setContentType("text/txt; charset=utf-8");

          PrintWriter out = null ;
          out = response.getWriter();
          out.write("123456С��");
          out.flush();
          out.close();*/
    	
    	  //response.sendRedirect("/404.html"); �ض���
    	
    	  System.out.println("�������е�����");
          return true;

    }
 

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	System.out.println("�����ط���trueʱ,���Ŵ����Ӧ·����ģ����ǣ����ã�������󴥷�����");
    }
 

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}