package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.repository.OrderRepository;
import com.chronos.chronosshop.repository.PaymentRepository;
import com.chronos.chronosshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.chronos.chronosshop.util.DateUtil.formatLocalDateTime;

@Controller
@RequestMapping("/admin/sales")
public class SalesAdminController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/invoice")
    public String invoiceSalesList(Model model) {
        model.addAttribute("listOrder", orderRepository.findAllByOrderByCreateTimeDesc());
        return "page/admin/sales/salesList";
    }

    @GetMapping("/invoice/{orderId}")
    public String detail(@PathVariable("orderId") String orderId, Model model) {
        model.addAttribute("listStatusInvoice", listStatusInvoice);
        model.addAttribute("listStatusPayment", listStatusPayment);
        model.addAttribute("order", orderService.findById(orderId));
        return "page/admin/sales/sales-details";
    }

    @PostMapping("/invoice/update/{orderId}")
    public String update(@PathVariable("orderId") String orderId,
                         @RequestParam("paymentIdUpdate") String paymentId,
                         @RequestParam("statusInvoice") String statusInvoice,
                         @RequestParam("statusPayment") String statusPayment) {
        orderRepository.updateOrderStatusByOrderId(statusInvoice, formatLocalDateTime(LocalDateTime.now()), orderId);
        paymentRepository.updatePaymentStatusByPaymentId(statusPayment, paymentId);
        return "redirect:/admin/sales/invoice/" + orderId;
    }

    List<String> listStatusInvoice = Arrays.asList("Đang xác nhận", "Đang chuẩn bị hàng", "Đang giao hàng", "Đã giao", "Giao hàng thất bại", "Bị trả hàng", "Huỷ bỏ");
    List<String> listStatusPayment = Arrays.asList("Chưa thanh toán", "Đã thanh toán", "Huỷ bỏ", "Hoàn tiền");
}
