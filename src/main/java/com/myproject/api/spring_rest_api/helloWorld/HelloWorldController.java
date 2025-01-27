package com.myproject.api.spring_rest_api.helloWorld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
private MessageSource messageSource;
    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
@GetMapping("/hello-world-internationalized")
//GET to http://localhost:8080/hello-world-internationalized with a Header Accept-Language: nl
public String helloWorldInternationalized() {
Locale locale = LocaleContextHolder.getLocale();//Determines the language and region
return
messageSource.getMessage("good.morning.message", null, "Default Message", locale );
}
}