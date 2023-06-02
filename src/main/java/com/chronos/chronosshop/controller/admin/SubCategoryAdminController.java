package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/subcategory")
public class SubCategoryAdminController {
    @GetMapping("/list")
    public String subCategorList(){
        return "admin/subcategorylist";
    }
    @GetMapping("/add")
    public String editSubCategory(){
        return "admin/editsubcategory";
    }
}
