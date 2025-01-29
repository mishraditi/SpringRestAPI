package com.myproject.api.spring_rest_api.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public FilterdBean bean(){
        return new FilterdBean("value1" ,"value2" ,"value3");
    }
}
