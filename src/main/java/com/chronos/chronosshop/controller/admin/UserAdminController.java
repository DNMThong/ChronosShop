package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String addUser(){
        return "page/admin/adduser";
    }
    @GetMapping("/")
    public String getAllUser(Model model){
        return "page/admin/userlists";
    }
}
