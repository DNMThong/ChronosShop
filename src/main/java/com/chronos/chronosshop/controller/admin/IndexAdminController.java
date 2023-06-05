package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class IndexAdminController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public String index(Model model) {
        PageRequest pageable = PageRequest.of(0, 5);
        model.addAttribute("listProduct", productRepository.findTop5ByOrderByCreateTimeDesc(pageable));
        return "admin/index";
    }
}
