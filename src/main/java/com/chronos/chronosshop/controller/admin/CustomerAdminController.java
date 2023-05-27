package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class CustomerAdminController {
    @GetMapping("addcustomer")
    public String addCustomer(){
        return "admin/addcustomer";
    }
    @GetMapping("customerlist")
    public String customerList(){
        return "admin/customerlist";
    }
}
