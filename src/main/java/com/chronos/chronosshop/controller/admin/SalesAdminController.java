package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class SalesAdminController {
    @GetMapping("add-sales")
    public String addSales(){
        return"admin/add-sales";
    }
    @GetMapping("saleslist")
    public String salesList(){
        return"admin/saleslist";
    }
}
