package com.didispace.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import net.sf.json.JSONObject;
import com.didispace.model.requestClass.RequestClass;;

class A{
	private int a = 1;
	protected int b = 2;
	public int c = 3;
	
	public int d() {
		return this.a;
	}
}

@RestController   //路由对应的方法返回的是字符串（spring会 把方法返回的转成字符串）
@RequestMapping("/http")
public class HttpRequest {
	
	/*private final RestTemplate restTemplate = new RestTemplate();
	private final String xiaoben = "xiaoming";*/
	//private final是只能内部访问和要初始化,上面就初始化了，也可以像下面那样初始化
	private final RestTemplate restTemplate ;
	private final String xiaoben;
	
	@Autowired
    public HttpRequest(RestTemplate restTemplate,String xiaoming) {
        this.restTemplate = restTemplate;
        this.xiaoben = "xiaoming";
    }
	 
	@RequestMapping("/get")
	public Object httpGet() {
		//System.out.println(this.xiaoben);
		A b = new A();
		System.out.println(b.c);
		System.out.println(b.b);
		System.out.println(b.d());
		
		/*ResponseEntity<String> resultResponseEntity = this.restTemplate.exchange(
	            String.format("https://www.baidu.com"),
	            HttpMethod.GET, null, String.class);
	    if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
	        return resultResponseEntity.getBody();
	    }
	       return null;
	    */
		
		 /*String url = "https://story.hhui.top/detail?id=666106231640";
		 String json = this.restTemplate.getForObject(url, String.class);
		 
		 JSONObject data = JSONObject.fromObject(json);
		 Object a = data.get("result");
		        return a;*/
		
		/*
		 *  post请求并携带参数
		 * */
		String url = "http://127.0.0.1:3000/index";
		List<Map<String,String>> array = new ArrayList<Map<String,String>>();
		
		Map<String,String> msgJson = new HashMap<String,String>(){{
			   put("sid", "101");
			   put("xiaoming","xiaoben");
			   put("我","你");
			   put("它","他");
		       put("公司","大山科技");
		}};
		array.add(msgJson);
				
		String json = this.restTemplate.postForObject(url,array,String.class);
		System.out.println(json);
		return 1;
	
	}
	
	@RequestMapping("/getTwo")
	public String httpGetTwo() {
		RequestClass info  = this.restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", RequestClass.class);
		return info.toString();
	} 
	
}
