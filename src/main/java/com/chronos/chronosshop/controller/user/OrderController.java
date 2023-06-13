package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.auth.Auth;
import com.chronos.chronosshop.entity.OrderDetail;
import com.chronos.chronosshop.entity.Orders;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.entity.dto.OrderDto;
import com.chronos.chronosshop.service.CartService;
import com.chronos.chronosshop.service.OrderDetailService;
import com.chronos.chronosshop.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@Transactional
public class OrderController {

    @Autowired
    Auth auth;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;


    @GetMapping("/order-detail")
    public String orderDetail(Model model) {
        Users user = auth.getUserLogin();
        System.out.println("USER ID ~ " + user.getUserId());
        return "page/order-detail-page";
    }

    @PostMapping("/order")
    @Transactional
    public String order(@ModelAttribute("orderDto") OrderDto orderDto) {
        Users user = auth.getUserLogin();
        System.out.println("RECIPIENT ~ " + orderDto.toString());

        Long totalProdPrice = 0L;
        for (int i = 0; i < user.getCarts().size(); i ++) {
            totalProdPrice += (long) user.getCarts().get(i).getQuantity() * user.getCarts().get(i).getProductVariant().getProduct().getPriceNew();
        }

        LocalDateTime now = LocalDateTime.now();
        Orders orders = null;
        try {
            orders = orderService.createOrderAndPayment(
                    user.getUserId(),
                    orderDto.getShipId(),
                    null,
                    "Chờ xác nhận",
                    now,
                    now,
                    orderDto.getPaymentMethod(),
                    totalProdPrice,
                    totalProdPrice >= 499500 ? 0L : 20000L,
                    totalProdPrice,
                    "VND"
            );
            if (orders != null) {
                Orders finalOrders = orders;
                user.getCarts().forEach(item -> {
                    orderDetailService.save(new OrderDetail(finalOrders, item.getProductVariant(), item.getProductVariant().getProduct().getPriceNew(), item.getQuantity()));
                });
                cartService.deleteCartsByUser_UserId(user.getUserId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("ORDER ID ~ " + orders.getOrderId() + "\t" + orders.getUser().getUserId());


        return "redirect:/";
    }

    @ModelAttribute("orderDto")
    public OrderDto orderDto() {
        return new OrderDto();
    }

}
