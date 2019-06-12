package helloWorld.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 和springmvc的webmvc拦截配置一样
 * @author BIANP
 */
@Configuration
public class RouteConfig implements WebMvcConfigurer {
	
   public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录	 
		//excludePathPatterns是设置不需要拦截哪个路由请求
		//addPathPatterns是设置拦截哪个路由请求
		System.out.println("路由处理优先级为第一");
		System.out.println("-----------拦截路由配置,所有的请求都得通过该配置才能释放----------");
        registry.addInterceptor(new RouteConfigConfig()).addPathPatterns("/**").excludePathPatterns("/test","/404.html","/public/**");
	 }
   
   public void addViewControllers(ViewControllerRegistry registry) {  
	   System.out.println("路由处理优先级为第二");
   	   System.out.println("---------------配置请求路由的对应时返回项目下的resources/one/index.html资源-----------------");
       registry.addViewController("/results").setViewName("/one/index");
       //registry.addViewController("/404").setViewName("/one/404");   
   }
	 
   public void addResourceHandlers(ResourceHandlerRegistry registry) { 
	    System.out.println("路由处理优先级为第三");
	    System.out.println("-----------静态资源访问配置------------");
	    //静态路径访问设置，也就是/public/xxx请求时，可以访问项目下的resources/public文件夹下的资源（文件），xxx对于文件夹下的资源
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
    }
	 
}