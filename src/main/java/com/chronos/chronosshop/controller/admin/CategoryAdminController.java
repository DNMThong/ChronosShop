package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Admin;
import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String categoryAll(@AuthenticationPrincipal Admin admin, Model model) {
        System.out.println(admin.getImage());
        model.addAttribute("listCategory", categoryRepository.listCategoryParent());
        return "page/admin/category/categoryList";
    }

    @GetMapping("/add")
    public String categoryAdd(Model model) {
        model.addAttribute("category", new Category());
        return "page/admin/category/addCategory";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category, BindingResult bindingResult) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{categoryId}")
    public String edit(@PathVariable("categoryId") Integer categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        return "page/admin/category/editCategory";
    }

    @GetMapping("/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") Integer subCategoryId) {
        categoryService.delete(subCategoryId);
        return "redirect:/admin/category";
    }
}
