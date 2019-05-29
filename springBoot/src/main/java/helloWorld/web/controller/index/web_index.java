package helloWorld.web.controller.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import helloWorld.web.controller.index.module.web_index_module_ValidForm;
import helloWorld.web.model.web_model_ebStoreProduct;;

@RequestMapping(value = "/")
@Controller
public class web_index {
	
	 @Autowired
	   private web_model_ebStoreProduct web_model_ebStoreProduct;
	
	 @GetMapping("form")
	    public String getForm(web_index_module_ValidForm validForm) {
		  // System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
		   return "web/index/form"; 
	    }
	
	 @PostMapping("form")   
	    public String postForm(@Valid web_index_module_ValidForm personForm, BindingResult bindingResult, Model model) {
	    	
	    	//model.addAttribute("validForm", new PersonForm());
	    	model.addAttribute("greeting", personForm);
	    	System.out.println(personForm.getName());
	    	System.out.println(personForm.getAge());
	        if (bindingResult.hasErrors()) {
	        	//System.out.println(bindingResult.getAllErrors());
	          //  System.out.println(personForm);
	        	
	            return "web/index/form";
	        }
	        //return "redirect:/results";
	         return "web/index/form";
	    }
	   
	   @GetMapping("index.html")
	      public String getIndex_html(HttpServletRequest request,HttpServletResponse response,Model model) {
		   
			   String sql = "select * from eb_store_productss";   
			   List<Map<String, Object>> list ;
			    try{
			    	 list = web_model_ebStoreProduct.select(sql);
			    	 model.addAttribute("storeProduct", list);
			    	 return "web/index/index";
			    }catch(final Exception e) {
			    	
			    	list = new ArrayList<Map<String, Object>>();
				    list.add(new HashMap<String,Object>() {{
				    		put("status","500");
				    		put("msg",e.getMessage());
				    	}});
					response.setStatus(500);
					return "web/index/index";
			    }
	   }
	   
}
