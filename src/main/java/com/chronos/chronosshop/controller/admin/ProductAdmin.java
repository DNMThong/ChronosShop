package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class ProductAdmin {
    @RequestMapping("addproduct")
    public String addproduct() {
        return "admin/addproduct";
    }
    @GetMapping("productlist")
    public String productlist() {
        return "admin/productlist";
    }
}
