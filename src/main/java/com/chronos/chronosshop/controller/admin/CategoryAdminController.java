package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {
    @RequestMapping("/add")
    public String addCategory() {
        return "admin/addcategory";
    }
    @RequestMapping("/list")
    public String categoryList() {
        return "admin/categorylist";
    }
}
