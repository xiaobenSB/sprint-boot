package com.didispace.model;

import com.didispace.model.web.twoApp;
import com.didispace.model.async.asyncGitHubLookupService;
import com.didispace.model.async.asyncUser;
import com.didispace.model.cache.cacheBook;
import com.didispace.model.cache.cacheSimpleBookRepository;
import com.didispace.model.web.threeApp;

import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * Hello world!
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController   //路由对应的方法返回的是字符串（spring会 把方法返回的转成字符串）

public class App {

	@RequestMapping("/test")
	String home(HttpServletRequest request,HttpSession session) {
		
	Object sessionBrowser = session.getAttribute("browser");
	
	if (sessionBrowser == null) {
	
	    System.out.println("不存在session，设置browser=" + "xiaoben");
	
	    session.setAttribute("browser", "xiaoben");
	
	} else {
	
	    System.out.println("存在session，browser=" + sessionBrowser.toString());
	
	}
	
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null && cookies.length > 0) {
	
	    for (Cookie cookie : cookies) {
	
	        System.out.println(cookie.getName() + " : " + cookie.getValue());
	
	    }
	
	}

	return "Hello Worldw!";
	}
	
	@RequestMapping("/bean")
	public Object  bean(HttpServletRequest request,HttpSession session) {
	    return  new bean().getBean("testDemo");
	}
	
	@Autowired  
    private twoApp twoapp;	
	@RequestMapping("/ceshi")
	public int ceshi() throws InterruptedException {
		//Thread.sleep(5000L);
	    return  twoapp.a();
	}
	
	@Autowired  
    private threeApp threeapp;
	@CrossOrigin(origins = "https://www.runoob.com")    //跨域设置     * 代表所有
	@RequestMapping("/ceshi2")
	public int ceshitwo(HttpServletRequest request,HttpServletResponse response) {
	        
		    //get方式获取
		
		    Map<String,String[]> requestMsg = request.getParameterMap();
	        Enumeration<String> requestHeader = request.getHeaderNames();

	        System.out.println("------- header -------");
	        while(requestHeader.hasMoreElements()){
	            String headerKey=requestHeader.nextElement().toString();
	            //打印所有Header值

	            System.out.println("headerKey="+headerKey+";value="+request.getHeader(headerKey));
	        }

	        System.out.println("------- parameter -------");
	        for(String key :requestMsg.keySet())
	        {
	            for(int i=0;i<requestMsg.get(key).length;i++)
	            {
	                //打印所有请求参数值

	                System.out.println("key="+key+";value="+requestMsg.get(key)[i].toString());
	            }
	        };
		
	    return  threeapp.a();
	}
	
	@Autowired 
	private asyncGitHubLookupService asyncGitHubLookupService;
	@RequestMapping("/ceshi3")
	public String ceshiThree() {
		//异步流程
				// Start the clock
		        long start = System.currentTimeMillis();
		        
		        // Kick of multiple, asynchronous lookups
		        CompletableFuture<asyncUser> page1;
		        CompletableFuture<asyncUser> page2;
		        CompletableFuture<asyncUser> page3;
				try {
					page1 = asyncGitHubLookupService.findUser("PivotalSoftware");
					page2 = asyncGitHubLookupService.findUser("CloudFoundry");
					page3 = asyncGitHubLookupService.findUser("Spring-Projects");
								
				    // Wait until they are all done
			        CompletableFuture.allOf(page1,page2,page3).join();

			        // Print results, including elapsed time
			        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
			        
			        try {
						 System.out.println("--> " + page1.get());
						 System.out.println("--> " + page2.get());
					     System.out.println("--> " + page3.get());
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		return "异步使用";
	}
	
	@Autowired
    JobLauncher jobLauncher;
	@Autowired
	Job importUserJob;
	@RequestMapping("/ceshi4")
	public String ceshiFour() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())  
                .toJobParameters();    
		//默认值假设是： 1,所有任务执行是都使用默认的话，都是 任务： 1
		//设置当前任务执行时间，等同该用户的id，如果不设就为默认（任务：（默认）固定值），下次执行就认为执行过了
		//springBatch执行同个任务时对应date值相同就不会执行了
		jobLauncher.run(importUserJob,jobParameters);
		return "启动soringBatch 任务管理";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	@Autowired
	cacheSimpleBookRepository bookRepository;
	@CrossOrigin(origins = "*")
	@RequestMapping("ceshi5")
	public cacheBook ceshiFive() {
		logger.info(".... Fetching books");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		return bookRepository.getByIsbn("isbn-1234"); 
	}

}

