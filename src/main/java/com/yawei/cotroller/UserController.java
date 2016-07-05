package com.yawei.cotroller;

import com.yawei.pojo.User;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;


@Controller
@RequestMapping(value = "/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(Model model,
                            @RequestParam(required = false,defaultValue = "false") String vip,
                            @RequestParam(required = false,defaultValue = "other",value = "userAge") String userAge,
                            @CookieValue(value = "JSESSIONID",required = false,defaultValue = "") String cookie) {
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("VIP:{}",vip);
        logger.debug("userAge:{}",userAge);
        logger.debug("cookie:{}",cookie);
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        model.addAttribute("userName","王石");
        return "users/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer id) {
        logger.debug("id为:{}", id);
        return "users/showuser";
    }

    @RequestMapping(value = "/{userid:\\d+}/photos/catalog/{catalogid:\\d+}", method = RequestMethod.GET)
    public String showPhoto(@PathVariable("userid") Integer userid, @PathVariable("catalogid") Integer catalogid) {
        logger.debug("userid为:{} catalogid为:{}", userid, catalogid);
        return "users/photos";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser() {
        return "/users/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveUser(User user) {
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("username:{} address:{}", user.getUsername(), user.getAddress());
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        return "redirect:/users";
    }

    @RequestMapping(value = "/{id:\\d+}/del", method = RequestMethod.GET)
    public String delUser(@PathVariable Integer id) {
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("del user id:{}", id);
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        return "redirect:/users";
    }

    @RequestMapping(value = "/check.json/{id:\\d+}", method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkUser(@PathVariable Integer id) {
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("check user id:{}", id);
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        return "成功";
    }

    @RequestMapping(value = "/{id}.json",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User showUserJson(@PathVariable Integer id){
        User user = new User();
        user.setUsername("马云");
        user.setAddress("杭州");

        return user;
    }

    @RequestMapping(value = "/header",method = RequestMethod.GET)
    public String httpHesder(HttpServletRequest request, HttpServletResponse response,
                             HttpSession session){
        ServletContext context = session.getServletContext();
        session.setAttribute("username","王健林");
        return "home";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String uploadImg(){
        return "/users/upload";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadImg(String imgName, MultipartFile file){
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("imgName:{}", imgName);
        logger.debug("ContentType:{}", file.getContentType());
        logger.debug("imgSize:{}", file.getSize());
        logger.debug("img isEmpty:{}", file.isEmpty());
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        try {
            IOUtils.copy(file.getInputStream(),new FileOutputStream("F:/upload/abc.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }

}
