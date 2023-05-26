package com.chronos.chronosshop.controller;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {

//    CategoryService categoryService;

//    List<Category> categoryList = null;

//    @Autowired
//    public TestController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }


    @RequestMapping("")
    public String index() {
        return "page/home-page";
    }

    @RequestMapping("productItem")
    public String productItem() {
        return "page/productItem";
    }

    @RequestMapping("login")
    public String login(Model model) {
        return "page/login-page";};

    @RequestMapping("sign-up")
    public String signUp(Model model) {

        return "page/sign-up-page";}

//    @ModelAttribute("femaleCategories")
//    public List<Category> getFemaleCategory() {
//        categoryList = categoryService.findCategoriesByName("Nữ");
//        return categoryList;
//    }

//    @ModelAttribute("maleCategories")
//    public List<Category> getMaleCategory() {
//        categoryList = categoryService.findCategoriesByName("Nam");
//        return categoryList;
//    }

//    @ModelAttribute("kidCategories")
//    public List<Category> getKidCategory() {
//        categoryList = categoryService.findCategoriesByName("Trẻ em");
//        return categoryList;
//    }

    @RequestMapping("addcategory")
    public String addcategory() {
        return "admin/addcategory";
    }
    @RequestMapping("addbrand")
    public String addbrand() {
        return "admin/addbrand";
    }
    @RequestMapping("add-sales")
    public String addsales() {
        return "admin/add-sales";
    }
    @RequestMapping("addproduct")
    public String addproduct() {
        return "admin/addproduct";
    }
    @RequestMapping("addcustomer")
    public String addcustomer() {
        return "admin/addcustomer";
    }
    @RequestMapping("adduser")
    public String adduser() {
        return "admin/adduser";
    }
    @RequestMapping("brandlist")
    public String brandlist() {
        return "admin/brandlist";
    }

    @RequestMapping("activities")
    public String activities() {
        return "admin/activities";
    }
    @RequestMapping("productlist")
    public String productlist() {
        return "admin/productlist";
    }
    @RequestMapping("categorylist")
    public String categorylist() {
        return "admin/categorylist";
    }

}
