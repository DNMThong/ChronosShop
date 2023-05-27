package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class BrandAdminController {
    @GetMapping("addbrand")
    public String addBrand(){
        return "admin/addbrand";
    }
    @GetMapping("brandlist")
    public String brandList(){
        return "admin/brandlist";
    }
}
