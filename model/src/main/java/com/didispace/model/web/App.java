package com.didispace.model.web;

/**
 * Hello world!
 *
 */
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@EnableAutoConfiguration
public class App {
@RequestMapping("/testsss")
String home() {
return "Hello World!";
}

}

