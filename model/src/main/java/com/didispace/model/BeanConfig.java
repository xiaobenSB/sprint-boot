package com.didispace.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {
    @Bean(name="testDemo")
    public String generateDemo() {
      
        return "xiaoben";
    }
}
