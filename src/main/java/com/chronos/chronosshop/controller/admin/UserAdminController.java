package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class UserAdminController {
    @GetMapping("adduser")
    public String addUser(){
        return"admin/adduser";
    }
    @GetMapping("userlists")
    public String userList(){
        return"admin/userlists";
    }
}
