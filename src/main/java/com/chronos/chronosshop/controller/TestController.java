package com.chronos.chronosshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {
        return "page/home-page";
    }

    @RequestMapping("/productItem")
    public String productItem() {
        return "page/productItem";
    }

    @RequestMapping("/login")
    public String login() {return "page/login-page";};

    

}
