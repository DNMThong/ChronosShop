package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Coupon;
import com.chronos.chronosshop.service.CouponService;
import com.chronos.chronosshop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/coupon")
public class CouponAdminController {
    @Autowired
    private CouponService couponService;

    @GetMapping("")
    public String couponList(Model model) {
        model.addAttribute("listCoupon", couponService.listAll());
        return "admin/coupon/couponList";
    }

    @GetMapping("/add")
    public String addCoupon(Model model) {
        model.addAttribute("coupon", new Coupon());
        return "admin/coupon/addCoupon";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("coupon") Coupon coupon) {
        try {
            couponService.save(coupon);
            return "redirect:/admin/coupon";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/coupon/add";
        }
    }

    @GetMapping("/edit/{couponId}")
    public String edit(@PathVariable("couponId") String couponId, Model model) {
        try {
            Coupon coupon = couponService.get(couponId);
            model.addAttribute("coupon", coupon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/coupon/addCoupon";
    }

    @GetMapping("/delete/{couponId}")
    public String delete(@PathVariable("couponId") String couponId) {
        try {
            couponService.delete(couponId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/coupon";
    }
}
