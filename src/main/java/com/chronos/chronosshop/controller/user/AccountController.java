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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@Validated
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String getLoginPage() {
        return "page/login-page";
    }

    @RequestMapping("/login?error")
    public String getLoginPageError() {
        return "page/login-page";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(@ModelAttribute("usersDto") UsersDto usersDto) {
        return "page/sign-up-page";
    }

    @PostMapping("/sign-up")
    public String postSignUpPage(@ModelAttribute("usersDto") @Validated UsersDto usersDto,BindingResult bindingResult,Model model) {
        if(!bindingResult.hasErrors()) {
            Users user = new Users();
            user.setFullname(usersDto.getFullname());
            user.setEmail(usersDto.getEmail());
            user.setPhone(usersDto.getPhone());
            user.setPassword(usersDto.getPassword());
            Boolean success = userService.save(user);
            model.addAttribute("success",success);
        }
        return "page/sign-up-page";
    }

    @RequestMapping("/social")
    public String loginGoogleSuccess(@RequestParam("success") Optional<Boolean> success, OAuth2AuthenticationToken auth2AuthenticationToken) {
        if(success.isPresent() && success.get()) {
            OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
            Map<String,Object> map =  oAuth2User.getAttributes();
            Users user = new Users();
            user.setEmail(String.valueOf(map.get("email")));
            user.setUserId(String.valueOf(map.get("sub")));
            user.setFullname(String.valueOf(map.get("name")));
            userService.saveUserFromGoogle(user);
        }
        return "redirect:/";
    }

}
