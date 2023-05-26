package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class CategoryAdmin {
    @RequestMapping("addcategory")
    public String addcategory() {
        return "admin/addcategory";
    }
    @RequestMapping("/categorylist")
    public String categorylist() {
        return "admin/categorylist";
    }
}
