package com.didispace.model.Form;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.didispace.model.PersonForm;;

@Controller
@RequestMapping("/form")
public class Form implements WebMvcConfigurer {

  
    public void addViewControllers(ViewControllerRegistry registry) {  
        registry.addViewController("/results").setViewName("/one/index");
        //registry.addViewController("/404").setViewName("/one/404");   
    }

    @GetMapping("")
    public String showForm(ValidForm personForm,Model model) {
    	//model.addAttribute("validForm", new PersonForm());
        return "one/form";
    }
  
    @PostMapping("")   
    public String checkPersonInfo(@Valid ValidForm personForm, BindingResult bindingResult, Model model) {
    	
    	//model.addAttribute("validForm", new PersonForm());
    	model.addAttribute("greeting", personForm);
    	System.out.println(personForm.getName());
    	System.out.println(personForm.getAge());
        if (bindingResult.hasErrors()) {
        	//System.out.println(bindingResult.getAllErrors());
          //  System.out.println(personForm);
        	
            return "one/form";
        }
        //return "redirect:/results";
        return "one/formResult";
    }
}