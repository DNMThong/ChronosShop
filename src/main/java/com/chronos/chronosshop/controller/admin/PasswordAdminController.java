package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class PasswordAdminController
{
    @GetMapping("forgetpassword")
    public String forgetPassword(){
        return "admin/forgetpassword";
    }

}
