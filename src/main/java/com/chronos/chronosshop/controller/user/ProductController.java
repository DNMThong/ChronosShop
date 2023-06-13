package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.service.ProductService;
import com.chronos.chronosshop.service.ProductVariantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductVariantService productVariantService;

    @Autowired
    HttpSession session;

    @GetMapping({"/{id}"})
    public String productDetail(@PathVariable("id") Optional<Integer> id, @RequestParam(value = "sku", defaultValue = "") String sku, Model model, HttpServletRequest req) {
        session.setAttribute("uri", req.getRequestURI());
        Product p = productService.findById(id.orElse(null));
        if (sku == null || sku.isEmpty()) {
            sku = p.getDistinctVariants().get(0).getImage().getProductSku();
        }
        model.addAttribute("product", p);
        model.addAttribute("variants", productVariantService.getVariantBySku(sku));
        return "page/product-item";
    }
}
