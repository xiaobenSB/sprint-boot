package com.didispace.model.web;

/**
 * Hello world!
 *
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class App1 {
@RequestMapping("/testsss")
String home() {
return "Hello World!";
}

}

