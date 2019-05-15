package com.didispace.model.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@EnableAutoConfiguration
public class twoApp {
@RequestMapping("/xiaoming")
String home() {
return "Hello World!";
}

}
