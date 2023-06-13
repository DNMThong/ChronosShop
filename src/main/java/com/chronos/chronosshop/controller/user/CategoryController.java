package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@Controller
@ControllerAdvice
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("category/*")
    public String getProducts(HttpServletRequest req) {
        System.out.println("URI " +req.getRequestURI());
        return "page/products-page";
    }

    @ModelAttribute("femaleCategories")
    public List<Category> getFemaleCategory() {
        return categoryService.findCategoriesByName("Nữ");
    }

    @ModelAttribute("maleCategories")
    public List<Category> getMaleCategory() {
        return categoryService.findCategoriesByName("Nam");
    }

    @ModelAttribute("kidCategories")
    public List<Category> getKidCategory() {
        return categoryService.findCategoriesByName("Trẻ em");
    }
}
