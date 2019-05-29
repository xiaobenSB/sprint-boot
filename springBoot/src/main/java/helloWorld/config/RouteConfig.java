package helloWorld.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * ��springmvc��webmvc��������һ��
 * @author BIANP
 */
@Configuration
public class RouteConfig implements WebMvcConfigurer {
	
   public void addInterceptors(InterceptorRegistry registry) {
        // ������������ͨ���ж��Ƿ��� @LoginRequired ע�� �����Ƿ���Ҫ��¼	 
		//excludePathPatterns�����ò���Ҫ�����ĸ�·������
		//addPathPatterns�����������ĸ�·������
		System.out.println("·�ɴ������ȼ�Ϊ��һ");
		System.out.println("-----------����·������,���е����󶼵�ͨ�������ò����ͷ�----------");
        registry.addInterceptor(new RouteConfigConfig()).addPathPatterns("/**").excludePathPatterns("/test","/404.html","/public/**");
	 }
   
   public void addViewControllers(ViewControllerRegistry registry) {  
	   System.out.println("·�ɴ������ȼ�Ϊ�ڶ�");
   	   System.out.println("---------------��������·�ɵĶ�Ӧʱ������Ŀ�µ�resources/one/index.html��Դ-----------------");
       registry.addViewController("/results").setViewName("/one/index");
       //registry.addViewController("/404").setViewName("/one/404");   
   }
	 
   public void addResourceHandlers(ResourceHandlerRegistry registry) { 
	    System.out.println("·�ɴ������ȼ�Ϊ����");
	    System.out.println("-----------��̬��Դ��������------------");
	    //��̬·���������ã�Ҳ����/public/xxx����ʱ�����Է�����Ŀ�µ�resources/public�ļ����µ���Դ���ļ�����xxx�����ļ����µ���Դ
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
    }
	 
}