package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class SubCategoryAdminController {
    @GetMapping("subcategorylist")
    public String subCategorList(){
        return "admin/subcategorylist";
    }
    @GetMapping("editsubcategory")
    public String editSubCategory(){
        return "admin/editsubcategory";
    }
}
