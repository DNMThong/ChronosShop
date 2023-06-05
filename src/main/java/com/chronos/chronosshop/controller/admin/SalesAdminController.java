package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.repository.OrderRepository;
import com.chronos.chronosshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sales")
public class SalesAdminController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/invoice")
    public String invoiceSalesList(Model model) {
        model.addAttribute("listOrder", orderRepository.findAll());
        return "admin/sales/salesList";
    }

    // đang xác nhận, đang giao hàng, hoàn thành,
    @GetMapping("/invoice/{orderId}")
    public String detail(@PathVariable("orderId") String orderId, Model model) {
        model.addAttribute("order", orderService.get(orderId));
        return "admin/sales/sales-details";
    }
}
