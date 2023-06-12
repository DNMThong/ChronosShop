package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.auth.Auth;
import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.entity.dto.AddressShippingDto;
import com.chronos.chronosshop.service.AddressShippingService;
import com.chronos.chronosshop.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("account")
public class UserAccountController {
    @Autowired
    Auth auth;

    @Autowired
    AddressShippingService addressShippingService;

    @Autowired
    UserService userService;

    @GetMapping({"/account", "/account/account"})
    public String account() {
        return "page/account-page";
    }

    @RequestMapping("/watched")
    public String accountWatched() {
        return "page/account-watched-page";
    }

    @RequestMapping("/my-order")
    public String accountMyOrdered() {
        return "page/account-myOrder-page";
    }


    @RequestMapping("/change-password")
    public String accountForgotPassword() {
        return "page/account-forgotPassword-page";
    }

    @GetMapping("/location-list")
    public String accountLocationList(@ModelAttribute("address")AddressShippingDto address ,Model model) {
        model.addAttribute("address", address != null ? address : new AddressShippingDto());
        model.addAttribute("action", "/account/add_address");
        return "page/account-locationList-page";
    }
    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/account/location-list";
    }
    @PostMapping("/add_address")
    public String addAddress(@ModelAttribute("address") AddressShippingDto address, Model model) {
        Users user = auth.getUserLogin();
        AddressShipping addressShipping = new AddressShipping();
        addressShipping.setUser(user);
        addressShipping.setRecipientName(address.getRecipientName());
        addressShipping.setRecipientPhone(address.getRecipientPhone());
        String a = String.join(", ", Arrays.asList(address.getNumber(), address.getWard(), address.getDistrict(),address.getProvince()));
        addressShipping.setRecipientAddress(a);
        addressShipping.setStatus(address.getStatus());
        addressShipping.setDeleted(false);
        model.addAttribute("address", addressShippingService.save(addressShipping));
        return "redirect:/account/location-list";
    }

    @GetMapping("/edit_address/{id}")
    public String editAddress(@PathVariable("id") Integer id, Model model) {
        AddressShippingDto addressShippingDto = new AddressShippingDto();
        AddressShipping addID = addressShippingService.findById(id);
        addressShippingDto.setRecipientName(addID.getRecipientName());
        addressShippingDto.setRecipientPhone(addID.getRecipientPhone());
        String[] a = addID.getRecipientAddress().split(", ");
        addressShippingDto.setCountry("Việt Nam");
        addressShippingDto.setNumber(a[0]);
        addressShippingDto.setWard(a[1]);
        addressShippingDto.setDistrict(a[2]);
        addressShippingDto.setProvince(a[3]);
        addressShippingDto.setStatus(addID.getStatus());
        model.addAttribute("address", addressShippingDto);
        model.addAttribute("show", true);
        model.addAttribute("action", "/account/edit_address/" + id);
        return "page/account-locationList-page";
    }

    @PostMapping("/edit_address/{id}")
    public String saveEditAddress(
            @ModelAttribute("address") AddressShippingDto address,
            @PathVariable("id") Integer id, Model model) {
        AddressShipping addressShipping= addressShippingService.findById(id);
        if(addressShipping != null) {
            addressShipping.setRecipientName(address.getRecipientName());
            addressShipping.setRecipientPhone(address.getRecipientPhone());
            String a = String.join(", ", Arrays.asList(address.getNumber(), address.getWard(), address.getDistrict(),address.getProvince()));
            addressShipping.setRecipientAddress(a);
            addressShipping.setStatus(address.getStatus());
            addressShipping.setDeleted(false);
            model.addAttribute("address", addressShippingService.save(addressShipping));
        }
        return "page/account-locationList-page";
    }
    @GetMapping("/delete_address/{id}")
    public String deleteAddress(
            @PathVariable("id") Integer id, Model model) {
        AddressShipping addressShipping= addressShippingService.findById(id);
        if(addressShipping != null) {
            model.addAttribute("action", "/account/delete_address/" + id);
            addressShipping.setDeleted(true);
            model.addAttribute("address", addressShippingService.save(addressShipping));
        }
        return "redirect:/account/location-list";
    }
    @RequestMapping("/favorite")
    public String accountFavorite() {
        return "page/account-favorite-page";
    }

    @ModelAttribute("addresses")
    public List<AddressShipping> getList() {
        Users user = auth.getUserLogin();
        return userService.findById(user.getUserId()).getAddressShippingList();
    }
}
