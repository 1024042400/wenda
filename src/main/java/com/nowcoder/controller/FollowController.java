package com.nowcoder.controller;

import com.nowcoder.model.HostHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowController {
    HostHolder hostHolder;

    @RequestMapping(value = "/followUser",method = RequestMethod.GET)
    @ResponseBody
    public String follow(@RequestParam("userId") int userId) {

    }
}
