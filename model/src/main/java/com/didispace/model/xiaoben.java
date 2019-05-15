package com.didispace.model;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@EnableAutoConfiguration
@Import({ App.class, cookie.class, thymeleafModel.class, jdbcMysql.class,WebConfigurer.class, thymeleafModeljdbc.class, bean.class, BeanConfig.class })

//这个会自动扫码当前目录下所有的xxx.java文件，等于上面的三个，但因为好像当前目录下不能有两个相同xxx.java文件，当前目录下里的不同文件夹也不允许有相同文件
//@SpringBootApplication   因为我们这里有两个相同文件，所有不用这个

public class xiaoben{
	
public static void main(String[] args) {

		SpringApplication.run(xiaoben.class,args);
		}
}