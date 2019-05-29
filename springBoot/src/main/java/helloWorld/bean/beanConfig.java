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

	        System.out.println("========RestTemplate�������õ�bean�����޶�Ϊʹ��autowire�󣬽�������RestTemplate���ͱ�����ѡ========");

	        System.out.println("-----------------------����HttpRequest.java���httpGet��������----------------------------------------------");
	        
	        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
	        httpRequestFactory.setConnectTimeout(30 * 3000);
	        httpRequestFactory.setReadTimeout(30 * 3000);
	        return new RestTemplate(httpRequestFactory);
	    }
	
	/*@Bean
    CommandLineRunner init(StorageService storageService){   
		System.out.println("-------------------��ʼ���ϴ�Ŀ¼-------------------------");
		//�ϴ��ļ���ʼ��
        return (args) -> {
            storageService.deleteAll();
        };
    }*/
  
}
