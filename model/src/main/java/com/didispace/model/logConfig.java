package com.didispace.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
 * */
@Configuration
public class logConfig {
    private static final Logger LOG = LoggerFactory.getLogger(logConfig.class);

    
    //@注入Bean的必须是一个有类型的方法，不能是void
    /*
              *    然后我们就可以使用
       @Autowired 
          private Logger xxx(变量名)    
                         来匹配注入Bean Logger类型的方法，
                         匹配到后就会执行匹配到的方法并返回（该方法里面的返回）给xxx
    */
 @Bean  
 public Logger logMethod() {
	    System.out.println("---------------------------------------------------------------------");

        System.out.println("========Logger类型配置到bean可以限定为使用autowire后，接着声明Logger类型变量候选========");

        System.out.println("----------------------可在thymeleafModel.java里的webSocket方法测试-----------------------------------------------");
        return LOG;
    }
}
