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
		public String httpGetTwo(@NotBlank(message = "uid����Ϊ�ղ���ΪString����") @Pattern(regexp = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", message = "uid��ʽΪ���֤") String uid,@NotBlank(message = "���ֲ���Ϊ�ղ���ΪString����") String name) {
			String url = "https://story.hhui.top/detail?id=666106231640";
			String json = restTemplate.getForObject(url, String.class);
			return json;
		}
	 
	 @GetMapping(value = "transaction")
	    public String getTransaction() {
		    try {
		    	transactionBookingService.book("xiao","ming","ben");
		    	System.out.println("��bookings�����xiao,ming,ben���ݳɹ�");
		    	
		    	transactionBookingService.book("xiao","hong","ben");
		    	System.out.println("��bookings�����xiao,hong,ben���ݳɹ�");
		    	return "mysql������Գɹ�";
		    }catch(Exception e) {
		    	return e.getMessage();
		    }
	 }
	 
}
