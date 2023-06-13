package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.auth.Auth;
import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@ControllerAdvice
@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    Auth auth;



    @ModelAttribute("user")
    public Users getUser() {
        return auth.getUserLogin();
    }

    @RequestMapping("/")
    public String index(Model model) {
//        List<Product> productList = productService.findAll();
        model.addAttribute("newestProducts", productService.getListNewestProduct(8));
        model.addAttribute("sportProducts", productService.getListProductContainName("sport"));
        model.addAttribute("polos", productService.getListProductContainName("polo"));
        model.addAttribute("jeans", productService.getListProductContainName("Jeans"));
        return "page/home-page";
    }
}
