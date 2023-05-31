package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class CouponAdminController {
    @GetMapping("addcoupon")
    public String addCoupon(){
        return "admin/addcoupon";
    }
    @GetMapping("couponlist")
    public String couponList(){
        return "admin/couponlist";
    }
}
