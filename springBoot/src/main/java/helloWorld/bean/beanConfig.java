package helloWorld.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import helloWorld.common.uploadFile.StorageService;
import helloWorld.common.uploadFile.StorageProperties;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class beanConfig {
	
	@Bean
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
	
	/*@Bean
    CommandLineRunner init(StorageService storageService){   
		System.out.println("-------------------初始化上传目录-------------------------");
		//上传文件初始化
        return (args) -> {
            storageService.deleteAll();
        };
    }*/
  
}
