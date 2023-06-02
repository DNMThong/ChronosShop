package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/customer")
public class CustomerAdminController {
    @GetMapping("/add")
    public String addCustomer(){
        return "admin/addcustomer";
    }
    @GetMapping("/list")
    public String customerList(){
        return "admin/customerlist";
    }
}