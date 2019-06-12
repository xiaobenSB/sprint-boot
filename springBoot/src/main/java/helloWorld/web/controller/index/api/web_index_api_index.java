package helloWorld.web.controller.index.api;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import helloWorld.common.transaction.transactionBookingService;

@RequestMapping(value = "/")
@RestController
@Validated
@CrossOrigin(origins = "*")
public class web_index_api_index {
	 @Autowired  
        private RestTemplate restTemplate;
	 @Autowired
	    private transactionBookingService transactionBookingService;
	
	 @GetMapping(value = "index")
		public String httpGetTwo(@NotBlank(message = "uid不能为空并且为String类型") @Pattern(regexp = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", message = "uid格式为身份证") String uid,@NotBlank(message = "名字不能为空并且为String类型") String name) {
			String url = "https://story.hhui.top/detail?id=666106231640";
			String json = restTemplate.getForObject(url, String.class);
			return json;
		}
	 
	 @GetMapping(value = "transaction")
	    public String getTransaction() {
		    try {
		    	transactionBookingService.book("xiao","ming","ben");
		    	System.out.println("往bookings表插入xiao,ming,ben数据成功");
		    	
		    	transactionBookingService.book("xiao","hong","ben");
		    	System.out.println("往bookings表插入xiao,hong,ben数据成功");
		    	return "mysql事务测试成功";
		    }catch(Exception e) {
		    	return e.getMessage();
		    }
	 }
	 
}
