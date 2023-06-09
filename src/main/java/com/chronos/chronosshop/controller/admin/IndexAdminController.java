package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class IndexAdminController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping({"", "/", "/dashboard"})
    public String index(@RequestParam(name = "year", defaultValue = "2023", required = false) int year,
                        Model model) {
        long startTime = System.currentTimeMillis();
        List<Object> listYear = paymentRepository.listYear();
        List<Object[]> list = paymentRepository.thongKeDoanhThuTheoNam(year);
        int totalDoanhThuNam = list.stream()
                .mapToInt(item -> Integer.parseInt(item[0].toString()))
                .sum();
        model.addAttribute("listDoanhThu", list);
        model.addAttribute("totalDoanhThuNam", totalDoanhThuNam);
        model.addAttribute("listYear", listYear);
        model.addAttribute("sumSanPhamDaBanTrongNam", orderDetailRepository.sumSanPhamDaBanTrongNam(year));
        model.addAttribute("totalProductsInStock", productVariantRepository.totalProductsInStock());
        model.addAttribute("countUser", userRepository.countUser());
        model.addAttribute("countOrders", orderRepository.countOrders(year));
        model.addAttribute("yearPresent", year);
        PageRequest pageable = PageRequest.of(0, 5);
        model.addAttribute("listProduct", productRepository.findTop5ByOrderByCreateTimeDesc(pageable));
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Thời gian thực thi: " + executionTime + "ms");
        return "page/admin/index";
    }
}