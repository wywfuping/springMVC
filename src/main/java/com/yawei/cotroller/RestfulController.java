package com.yawei.cotroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestfulController {

    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String showSuccess(){
        return "success";
    }
}
