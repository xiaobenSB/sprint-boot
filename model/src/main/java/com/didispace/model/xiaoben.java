package com.didispace.model;

import java.util.concurrent.Executor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;


import com.didispace.model.uploadFile.StorageService;
import com.didispace.model.uploadFile.StorageProperties;
import com.didispace.model.web.threeApp;

/*@Configuration
@EnableAutoConfiguration
@Import({ App.class, cookie.class, thymeleafModel.class, jdbcMysql.class,WebConfigurer.class, thymeleafModeljdbc.class, bean.class, BeanConfig.class, HttpRequest.class, socketConfig.class, webSocket.class, logConfig.class, Form.class })
*/
//这个会自动扫码当前目录下所有的xxx.java文件，等于上面的三个，但因为好像当前目录下不能有两个相同xxx.java文件，当前目录下里的不同文件夹也不允许有相同文件
//  因为我们这里有两个相同文件，所有不用这个
@SpringBootApplication 
@EnableConfigurationProperties(StorageProperties.class)    //对应@ConfigurationProperties注解
@EnableAsync       //设置这个才会把@Async的默认配置换成下面的 @Bean public Executor taskExecutor()
@EnableCaching     //管理缓存并拦截对应缓存的方法
public class xiaoben{
	
	@Bean
	public threeApp xiaobenssss() {
      return new threeApp();
    }
	
	@Bean
    public Executor taskExecutor() {     //修改@Aysnc对应的并发池配置
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); //并发数量限制为2
        executor.setMaxPoolSize(2);  //并发数量限制为2
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }
	
	@Bean
    CommandLineRunner init(StorageService storageService){
		  
		//上传文件初始化
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
	
	@Bean //必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
	  public RestTemplate restTemplate() {
		    System.out.println("---------------------------------------------------------------------");

	        System.out.println("========RestTemplate类型配置到bean可以限定为使用autowire后，接着声明RestTemplate类型变量候选========");

	        System.out.println("-----------------------可在HttpRequest.java里的httpGet方法测试----------------------------------------------");
	        
	        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
	        httpRequestFactory.setConnectTimeout(30 * 3000);
	        httpRequestFactory.setReadTimeout(30 * 3000);
	        return new RestTemplate(httpRequestFactory);
	    }
	
		public static void main(String[] args) {
		   
			SpringApplication.run(xiaoben.class,args);
		}
}