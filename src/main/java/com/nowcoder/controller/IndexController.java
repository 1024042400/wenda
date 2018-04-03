package com.nowcoder.controller;

import com.nowcoder.model.User;
import com.nowcoder.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

//    @RequestMapping(path = {"/","/index"}, method = {RequestMethod.GET})
//    @ResponseBody
//    public String index() {
//        return "Hello nowcoder12";
//    }

    @RequestMapping(path = {"/tem"},method = {RequestMethod.GET})
    public RedirectView template(Model model, HttpServletRequest request, HttpServletResponse response){
        StringBuilder sb = new StringBuilder();
        Enumeration<String> names =  request.getHeaderNames();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            sb.append(name +" : "+request.getHeader(name) + "<br/>");
        }
        model.addAttribute("headers",sb.toString());
        model.addAttribute("value1","1231aaa");
        List list = Arrays.asList(new String[]{"red","blue"});

        model.addAttribute("colors",list);
        User user = new User("mingzi");
        model.addAttribute("user",user);
//        return "home";
        RedirectView rv = new RedirectView("/");
        rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//        return "redirect:/";
        return rv;
    }

    @RequestMapping(path={"admin"})
    @ResponseBody
    public String admin(@RequestParam("key") String key) {
        if(key.equals("123")) {
            return "admin";
        }
        throw new IllegalArgumentException("参数不对啊");
    }

    @ExceptionHandler
    @ResponseBody
    public String error(Exception e) {
        return "error:"+ e.getMessage();
    }
}
