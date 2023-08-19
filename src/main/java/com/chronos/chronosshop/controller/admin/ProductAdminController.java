package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.repository.ProductRepository;
import com.chronos.chronosshop.repository.ProductVariantRepository;
import com.chronos.chronosshop.service.ProductService;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("/list")
    public String productList( Model model) {
        model.addAttribute("products", productService.findAll());
        return "page/admin/productlist";
    }
    @GetMapping("/add")
    public String addProduct( Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("title","Thêm");
        return "page/admin/addproduct";
    }
    @PostMapping("/add")
    public String productSave(@Valid Product product, BindingResult result, RedirectAttributes redirectAttributes,Model model) {
        if(result.hasErrors()){
            model.addAttribute("message", "Có một số trường không hợp lệ vui lòng kiểm tra lại!");
            model.addAttribute("type","error");
            model.addAttribute("show",true);
            return "page/admin/addproduct";
        }else {
            productService.save(product);
            redirectAttributes.addFlashAttribute("message","Lưu sản phẩm thành công!");
            redirectAttributes.addFlashAttribute("type","success");
            redirectAttributes.addFlashAttribute("show",true);
            return "redirect:/admin/product/list";
        }
    }
    @GetMapping("/edit/{id}")
    public String productUpdate(@PathVariable("id") Integer id,Model model,RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product!=null){
            model.addAttribute("product",product);
            model.addAttribute("title","Cập nhật");
            return "page/admin/addproduct";
        }
        redirectAttributes.addFlashAttribute("message","Không tìm thấy sản phẩm");
        return "redirect:/admin/product/list";
    }
    @GetMapping("/delete/{id}")
    public String productDelete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            productService.updateStatusDeleted(id);
            redirectAttributes.addFlashAttribute("message","Xóa sản phẩm thành công");
            redirectAttributes.addFlashAttribute("type","success");
            redirectAttributes.addFlashAttribute("show",true);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/admin/product/list";

    }
    @ModelAttribute("categoryList")
    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }
    @ModelAttribute("sizeProduct")
    public List<String> sizeProduct(){
        return Arrays.asList("S","M","L","XL","2XL","3XL","XXL");
    }

}
