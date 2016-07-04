package com.yawei.cotroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private Logger logger= LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String showHome(){
        logger.debug("走吧，走吧");
        return "home";
    }
}
