package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String categoryAll(Model model) {
        model.addAttribute("listCategory", categoryRepository.listCategoryParent());
        return "page/admin/category/categoryList";
    }

    @GetMapping("/add")
    public String categoryAdd(Model model) {
        model.addAttribute("category", new Category());
        return "page/admin/category/addCategory";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes ra) {
        try {
            categoryService.save(category);
            return "redirect:/admin/category";
        } catch (Exception e) {
            ra.addFlashAttribute("messageFail", "Tạo danh mục mới thất bại!!!");
            return "redirect:/admin/category/add";
        }
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
