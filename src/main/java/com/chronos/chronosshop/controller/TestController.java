package com.chronos.chronosshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    @RequestMapping("/hello")
    public String index() {
        return "page/home-page";
    }

    @RequestMapping("/login")
    public String login() {return "page/login-page";};


}
