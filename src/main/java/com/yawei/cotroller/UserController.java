package com.yawei.cotroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(){
        return "users/list";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer id){
        logger.debug("id为:{}",id);
        return "users/showuser";
    }

    @RequestMapping(value = "/{userid:\\d+}/photos/catalog/{catalogid:\\d+}",method = RequestMethod.GET)
    public String showPhoto(@PathVariable("userid") Integer userid,@PathVariable("catalogid") Integer catalogid){
        logger.debug("userid为:{} catalogid为:{}",userid,catalogid);
        return "users/photos";
    }
}
