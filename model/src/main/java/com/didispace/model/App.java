package com.didispace.model;

import com.didispace.model.web.twoApp;
import com.didispace.model.async.asyncGitHubLookupService;
import com.didispace.model.async.asyncUser;
import com.didispace.model.cache.cacheBook;
import com.didispace.model.cache.cacheSimpleBookRepository;
import com.didispace.model.web.threeApp;
import com.didispace.model.transaction.transactionBookingService;
import com.didispace.model.web.JSON;
import com.didispace.model.mysql.ebStoreProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * Hello world!
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//支付宝sdk

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.domain.AlipayTradeCreateModel;

@RestController   //路由对应的方法返回的是字符串（spring会 把方法返回的转成字符串）

public class App {
    
	@RequestMapping("/hello")
	String hello(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String out_trade_no = "11233444838338";
		// 订单名称，必填
	    String subject = "小明";
		System.out.println(subject);
	    // 付款金额，必填
	    String total_amount= "100";
	    // 商品描述，可空
	    String body = "";
	    // 超时时间 可空
	   String timeout_express="2m";
	    // 销售产品码 必填
	    String product_code="QUICK_WAP_WAY";
	    /**********************/
	    // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
	    //调用RSA签名方式
	    
	    //下面str是拿E:\web\php\public\5cd24539c1663.pem  的数据（私钥pkcs8格式）  不是私钥pkcs8格式数据的话 ，会报出DER input, Integer tag error 或 Detect premature EOF等错误   
	    String str = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDsPewrnrdfw3QQFF3Bg/X7MrZtQuNhiMSk3rx/Jq/mguOW1jnt5lSEG2VFhesT/QHyMPgasIHrlPrQD4PGhCdGkXbs4oQLoUTqAwkyJpocr5CwiKzrmLc2p3qqg2E5hanv0WMqFzJ3RuNoeST1vB52xJdgkrPz8Rslo8o1UNj0SW/bj0QU3UskF2V+qmD8jjAcnX7wsFU4fufUPFkX2aOOE4oV/W/rPoXfas4ChM4KjD6lN92QkCeZYpr9rkzThSkm9X+H2sF8qNcebdEGJ/gpHIMCbtlNjkEJJhRFC03zapOB261qOuEftzrtTv6zcqPlYkWPy459nbOLvtjJELFlAgMBAAECggEBAK/fsbEPqgjbI87MurfUnA30xSc2gr0b6vmq8L3geVes3e5vchUQQp9PHefSOR5aX1aE6lBEU5SlBsxaoInr3KmGpfjY8eEOoJVuySvS3Sy574fdWI7U1KEVsha7VGhUgB0PzzmIp4Nw/N/MQJ3I/Q0Ccofs75eOSd2NwH5MwBesHhzlYGvwUgIZkk4IW6twk9aP48+Zc7bjzs/V0nr1wO9Y8yzwAWCWWyXCn2hzi+36DTTyajcXVq0hMRZFP23gk1vhViu1Fd6CmYII1il7EG9NFURSBtOP+EOWmzlkdRePqbWh7fisw0bSLw/OphFMMxwbZpWXpFJLj12zIuDZZQkCgYEA+6JKvLY51ubBH9P9N8sciCvzja8a13k8E2sTUaXfCSz53XaiA4FhtnNTbEoo6QeBO6mXtaLdbI8kGNJ7/Vt6gRqLhQ6b5efyIzUYQ1U4xh33XEtgf1UcTksnMT4VwYeXDFM+bt2cvtmx+whLdpkajJhfKNds/rT4gSjXb8H5+4sCgYEA8FdDGD4Hpo5PgFyI8qWJi0ZurlwjNIS9ILmFByW1J5SFcciSOKTMSJ3FWtdKRfJxEdOdONjsJ55V5frD4lYdWaVRVp2UWqzg+OqXdwh1PuWO7WPE7gLHCLq027PRN0XtvDuTf14FTNmEDyPE54ZTUxqw43BhMjLzHb+lmYKEZM8CgYEAp+OaVdqHMLj5NZEtK6KawMgCUg/4qrc6vAH++8Td0LNvarGSWyBh32eGy4OXVBMryHDYxdmKProqbV1SWLJGRAk/+WDL51MgHRl5vMMJhDXOKogoNAzHO/2sgpBX163tu812pGW8BSIeO81G/DQeoJuxMgC5uh9ohlSHmQslDQ0CgYEAuFaVtZBOGedo/tD6kPF6j4JT/hPZRLzSurjQWW0IhvUZbO4jiKKNtNydtFEQPJn5M20VV7a5WqHMzHoLqBvYoxtzSXXPhcS3QPPdfITWOImlmFo/fzZOJlndwe0neLd/4jHnrXcVpZ3n9hy/N70FR+Ze6fMV+YSuh5ComRdMKG0CgYAOoYN9xZcLI8glaA1BEsJiO2DiPC+szQUOxFzC09rZqcacK8aZnWk5tOKZYig8MNgTbaAe8EuwtqhwAQpZwQv1k0KJVWEOZW66uxAbPhw5Ee4YwkeyiaO6N1ls2OguLMDlY7tgu/syxGYQypu5EQ2BlM/KuAzD/pVU1TysnZzMVA==";

	    
	    AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "425111044", str, "json", "UTF-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrEVFMOSiNJXaRNKicQuQdsREraftDA9Tua3WNZwcpeXeh8Wrt+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB","RSA2");
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    // 封装请求支付信息
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(out_trade_no);
	    model.setSubject(subject);
	    model.setTotalAmount(total_amount);
	    model.setBody(body);
	    model.setTimeoutExpress(timeout_express);
	    model.setProductCode(product_code);
	    alipay_request.setBizModel(model);
	    
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl("http://127.0.0.1:8000/testssssss");
	    // 设置同步地址
	    alipay_request.setReturnUrl("http://127.0.0.1:8000/testssssss");   
	    
	    // form表单生产
	    String form = "";
		try {
			// 调用SDK生成表单
			
			form = client.pageExecute(alipay_request).getBody();
			System.out.println(form);
			response.setContentType("text/html;charset=UTF-8");
			
			return form;  //会自己发送请求
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			System.out.println("311333311111111111");
			e.printStackTrace();
			return e.getMessage();
		} 
	}
	
	@RequestMapping("/testssssss")
	String testssssss() {
		System.out.println("111111111111111111111111111");
		return "wwwww";
	}
	
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

	return "Hello Worldw2!";
	}
	
	/*
	 *           Bean获取
	 * */
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
	
	/*
	   *                      跨域设置和获取请求头和get挈带值
	 * */
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
	
	/*
	   *                      异步测试
	 * */
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
	
	/*
	 *             springBatch任务管理
	 * */
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
	
	/*
	   *                      缓存测试
	 * */
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	@Autowired
	cacheSimpleBookRepository bookRepository;
	@CrossOrigin(origins = "*")
	@RequestMapping("/ceshi5")
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
	
	/*
	   *                      缓存更新
	 * */
	@CrossOrigin(origins = "*")
	@RequestMapping("/ceshi5Two")
	public cacheBook ceshiFiveTwo() {
		logger.info(".... Fetching books");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		return bookRepository.getByIsbnTwo("isbn-1234"); 
	}
	
	/*
	 *          mysql事务测试
	 * */
	@Autowired
	transactionBookingService transactionBookingService;
	@RequestMapping("/ceshi6")
	public String ceshiSix() {
		
		//第一次插入三条数据成功
        transactionBookingService.book("Alice", "Bob", "Carol");
       logger.info("Alice, Bob and Carol have been booked");
       
        //第二次插入两条数据时，由于我们数据库字段长度限制为5，Samuel为6了就会报错，然后进行回滚
        try {
        	transactionBookingService.book("Chris", "Samuel");
        } catch (RuntimeException e) {
            logger.info("v--- The following exception is expect because 'Samuel' is too " +
                    "big for the DB ---v");
            logger.error(e.getMessage());
        }
        for (String person : transactionBookingService.findAllBookings()) {
            logger.info("So far, " + person + " is booked.");
        }
        logger.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
                "and Chris was rolled back in the same TX");

        //第三次插入两条数据时，由于我们数据库字段设置不能为空，这里为null了就会报错，然后进行回滚
        try {
        	transactionBookingService.book("Buddy", null);
        } catch (RuntimeException e) {
            logger.info("v--- The following exception is expect because null is not " +
                    "valid for the DB ---v");
            logger.error(e.getMessage());
        }
        for (String person : transactionBookingService.findAllBookings()) {
            logger.info("So far, " + person + " is booked.");
        }
        logger.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
                "Buddy was rolled back in the same TX");
        
        
		return "mysql事务测试";
	}
	
	@Autowired
	private ebStoreProduct ebStoreProduct;
	@RequestMapping("/ceshi7")
	@CrossOrigin(origins = "*")
	public List<Map<String, Object>> ceshiSeven(HttpServletRequest request,HttpServletResponse response) {
		
		String sql = "select * from eb_store_productss";   	    
	    try{
	    	//return ebStoreProduct.select(sql);
	    	int msg = ebStoreProduct.insert("xiaob");
	    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
								      list.add(new HashMap<String,Object>() {{
								    		put("status","200");
								    		put("msg",msg);
								    	}});
		    response.setStatus(500);
	    	return list;
	  
	    }catch(Exception e) {
	    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
								      list.add(new HashMap<String,Object>() {{
								    		put("status","500");
								    		put("msg",e.getMessage());
								    	}});
			response.setStatus(500);
	    	return list;
	    }
	    
	   //return new JSON("xiaoming","xiaoben","wwwww");
	}

}

