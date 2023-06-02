package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiMenuItemUI;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/add")
    public String addCategoryGet(Model model) {
        return "admin/addcategory";
    }

    @PostMapping("/add")
    public String addCategoryPost(Model model,Category category) {
        Boolean isSuccess = categoryService.save(category);
        if(isSuccess) {
            model.addAttribute("show",true);
            model.addAttribute("type","success");
            model.addAttribute("message","Thêm danh mục thành công");
        }else {
            model.addAttribute("show",true);
            model.addAttribute("type","error");
            model.addAttribute("message","Thêm danh mục thất bại");
        }

        return "admin/addcategory";
    }

    @RequestMapping("/list")
    public String categoryList(Model model) {
        List<Category> categories = categoryService.listAll();
        model.addAttribute("categories",categories);
        return "admin/categorylist";
    }
}
