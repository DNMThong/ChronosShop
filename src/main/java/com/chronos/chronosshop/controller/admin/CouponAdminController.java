package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/coupon")
public class CouponAdminController {
    @GetMapping("/add")
    public String addCoupon(){
        return "admin/addcoupon";
    }
    @GetMapping("/list")
    public String couponList(){
        return "admin/couponlist";
    }
}
