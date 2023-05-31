package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
    @GetMapping("/add")
    public String addUser(){
        return"admin/adduser";
    }
    @GetMapping("/list")
    public String userList(){
        return"admin/userlists";
    }
}
