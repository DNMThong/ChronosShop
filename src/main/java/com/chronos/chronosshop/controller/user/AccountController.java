package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("account")
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("login")
    public String getLoginPage() {
        return "page/login-page";
    }

    @PostMapping("login")
    public String doLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
        Users user = userService.getUserByEmail(email);
        System.out.println(user.getEmail());
        return "page/login-page";
    }
}
