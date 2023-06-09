package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.entity.dto.UsersDto;
import com.chronos.chronosshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String getLoginPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        model.addAttribute("usernameError",session.getAttribute("error-username"));
        model.addAttribute("passwordError",session.getAttribute("error-password"));
        return "page/login-page";
    }

    @RequestMapping("/login?error")
    public String getLoginPageError(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        model.addAttribute("usernameError",session.getAttribute("error-username"));
        model.addAttribute("passwordError",session.getAttribute("error-password"));
        return "page/login-page";
    }

}
