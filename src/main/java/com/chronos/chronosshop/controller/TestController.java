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

    @RequestMapping("/addcategory")
    public String addcategory() {
        return "admin/addcategory";
    }
    @RequestMapping("/addbrand")
    public String addbrand() {
        return "admin/addbrand";
    }
    @RequestMapping("/add-sales")
    public String addsales() {
        return "admin/add-sales";
    }
    @RequestMapping("/addproduct")
    public String addproduct() {
        return "admin/addproduct";
    }
    @RequestMapping("/addcustomer")
    public String addcustomer() {
        return "admin/addcustomer";
    }
    @RequestMapping("/adduser")
    public String adduser() {
        return "admin/adduser";
    }
    @RequestMapping("/brandlist")
    public String brandlist() {
        return "admin/brandlist";
    }

    //    t----t
    @RequestMapping("/activities")
    public String activities() {
        return "admin/activities";
    }
    @RequestMapping("/productlist")
    public String productlist() {
        return "admin/productlist";
    }
    @RequestMapping("/categorylist")
    public String categorylist() {
        return "admin/categorylist";
    }

}
