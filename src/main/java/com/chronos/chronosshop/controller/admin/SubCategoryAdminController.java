package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/subcategory")
public class SubCategoryAdminController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String subcategoryAll(Model model) {
        List<Category> list = categoryRepository.listCategoryChildren();
        int total = 0;
        for (Category item : list) {
            total += item.getProducts().size();
        }
        model.addAttribute("listCategory", list);
        model.addAttribute("sumCountProduct", total);
        return "admin/subcategory/subCategoryList";
    }

    @GetMapping("/add")
    public String subcategoryAdd(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("listCategoryParent", categoryRepository.listCategoryParent());
        return "admin/subcategory/addSubcategory";
    }

    @PostMapping("/save")
    public String saveSubcategory(@ModelAttribute("category") Category category, RedirectAttributes ra) {
        try {
            categoryService.save(category);
            return "redirect:/admin/subcategory";
        } catch (Exception e) {
            ra.addFlashAttribute("messageFail", "Tạo danh mục phụ mới thất bại!!!");
            return "redirect:/admin/subcategory/add";
        }
    }

    @GetMapping("/edit/{subCategoryId}")
    public String edit(@PathVariable("subCategoryId") Integer subCategoryId, Model model) {
        model.addAttribute("listCategoryParent", categoryRepository.listCategoryParent());
        Category category = categoryService.get(subCategoryId);
        model.addAttribute("category", category);
        return "admin/subcategory/editSubcategory";
    }

    @GetMapping("/delete/{subCategoryId}")
    public String delete(@PathVariable("subCategoryId") Integer subCategoryId) {
        categoryService.delete(subCategoryId);
        return "redirect:/admin/subcategory";
    }
}
