package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class CategoryAdminController {
    @RequestMapping("addcategory")
    public String addCategory() {
        return "admin/addcategory";
    }
    @RequestMapping("/categorylist")
    public String categoryList() {
        return "admin/categorylist";
    }
}
