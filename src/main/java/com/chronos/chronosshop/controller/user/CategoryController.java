package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@ControllerAdvice
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

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

    @GetMapping("/{categorySlug}")
    public String productsCategory(Model model, @PathVariable("categorySlug") String slug) {
        Map<String,List<String>> optionFilter = new HashMap<>();
        List<Product> products = categoryService.findProductsBySlug("/"+slug);

        model.addAttribute("products",products);
        model.addAttribute("optionFilter",optionFilter);
        model.addAttribute("slug",slug);
        return "page/products-page";
    }

    @PostMapping("/{categorySlug}")
    public String filterProductsCategory(Model model, @PathVariable("categorySlug") String slug,
                                         @RequestParam("color") Optional<List<String>> color,
                                         @RequestParam("size") Optional<List<String>> size,
                                         @RequestParam("aboutPrice") Optional<List<String>> aboutPrice ) {
        Map<String,List<String>> optionFilter = new HashMap<>();

        if(color.isPresent())  optionFilter.put("color",color.get());
        if(size.isPresent())  optionFilter.put("size",size.get());
        if(aboutPrice.isPresent())  optionFilter.put("aboutPrice",aboutPrice.get());

        AtomicReference<List<Product>> products = new AtomicReference<>(categoryService.findProductsBySlug("/" + slug));

        optionFilter.forEach((key,value) -> {
            switch (key) {
                case "size": {
                    products.set(products.get().stream().filter(product ->
                            product.getProductVariants()
                                    .stream().filter(item ->
                                            value.contains(item.getProductSize())).findAny().isPresent()
                    ).toList());
                    break;
                }
                case "color" : {
                    products.set(products.get().stream().filter(product ->
                            product.getProductVariants()
                                    .stream().filter(item ->
                                            value.contains(item.getProductColorName())).findAny().isPresent()
                    ).toList());
                    break;
                }
                case "aboutPrice" : {
                    products.set(products.get().stream().filter(product -> {
                        Integer price = product.getPriceNew();
                        Boolean isSuccess = false;
                        for(String item : value) {
                            String[] prices = item.split("-");
                            if(prices.length==2) {
                                isSuccess = Integer.parseInt(prices[0]) <= price && price <= Integer.parseInt(prices[1]);
                            }else if(prices.length==1) {
                                isSuccess = price >= Integer.parseInt(prices[0]);
                            }
                            if(isSuccess) return isSuccess;
                        }
                        return false;
                    }).toList());
                    break;
                }
                default: break;
            }
        });
        model.addAttribute("products",products.get());
        model.addAttribute("optionFilter",optionFilter);
        model.addAttribute("slug",slug);
        return "page/products-page";
    }
}
