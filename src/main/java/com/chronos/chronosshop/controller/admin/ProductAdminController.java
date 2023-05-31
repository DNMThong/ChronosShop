package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class ProductAdminController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("productlist")
    public String productList( Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/productlist";
    }
    @RequestMapping("addproduct")
    public String addProduct( Model model) {
        model.addAttribute("product", new Product());
        return "admin/addproduct";
    }
    @RequestMapping("addproduct/save")
    public String productSave(Product product) {
        productRepository.save(product);
        return "redirect:/admin/productlist";
    }
    @RequestMapping("productlist/delete/{id}")
    public String productDelete(@PathVariable("id") Integer id) {
        Product product = productRepository.getById(id);
        if(product!=null){
            product.setStatus("Đã xóa");
            productRepository.save(product);
        }
        return "redirect:/admin/productlist";
    }
    @RequestMapping("addproduct-variant")
    public String addProductVariant() {

        return "admin/addproduct-variant";
    }
    @RequestMapping("editproduct")
    public String editproduct(){
        return"admin/editproduct";
    }


    @ModelAttribute("categoryList")
    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }


}
