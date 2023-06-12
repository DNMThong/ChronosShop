package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.entity.dto.PasswordDto;
import com.chronos.chronosshop.service.MailerService;
import com.chronos.chronosshop.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Validated
@Controller
public class PasswordController {
    @Autowired
    MailerService mailerService;

    @Autowired
    UserService userService;

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "page/forgot-password-page";
    }

    @PostMapping("/forgot-password")
    public String sendMailResetPasssoword(Model model,@RequestParam("mail") String mail) throws MessagingException {
        String token = userService.linkTokenResetPassword(mail);
        if(token!=null) {
            String body = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Reset Password</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f0f0f0;\n" +
                    "            padding: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #fff;\n" +
                    "            padding: 20px;\n" +
                    "            border-radius: 5px;\n" +
                    "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "\n" +
                    "        h1 {\n" +
                    "            color: #fcaf17;\n" +
                    "        }\n" +
                    "\n" +
                    "        p {\n" +
                    "            margin-bottom: 10px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .btn {\n" +
                    "            display: inline-block;\n" +
                    "            padding: 10px 20px;\n" +
                    "            background-color: #fcaf17;\n" +
                    "            color: white;\n" +
                    "            text-decoration: none;\n" +
                    "            border-radius: 3px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1>Reset Your Password</h1>\n" +
                    "        <p>Click the button below to reset your password:</p>\n" +
                    "        <a href=\""+"http://localhost:8080/reset-password?"+token+"\" class=\"btn\">Reset Password</a>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n";
            mailerService.send(mail,"Thiết lập lại mật khẩu của tài khoản khách hàng",body);
            model.addAttribute("message","Vui lòng check mail của bạn");
            return "page/forgot-password-page";
        }

        return "redirect:/account/login";
    }

    @GetMapping("/reset-password")
    public String getFormResetPassword(Model model,@ModelAttribute("passwordDto") PasswordDto passwordDto,@RequestParam("tokenKey") Optional<String> key, @RequestParam("tokenValue") Optional<String> value) {
        if(key.isEmpty() ||value.isEmpty()) {
            return "redirect:/account/login";
        }
        model.addAttribute("tokenKey",key.get());
        model.addAttribute("tokenValue",value.get());
        return "page/reset-password-page";
    }

    @PostMapping("/reset-password")
    public String postFormResetPassword(Model model, @ModelAttribute("passwordDto") @Validated PasswordDto passwordDto, BindingResult bindingResult, @RequestParam("tokenKey") Optional<String> key, @RequestParam("tokenValue") Optional<String> value) {
        if(key.isEmpty() ||value.isEmpty()) return "redirect:/account/login";
        if(!passwordDto.getPassword().equals(passwordDto.getConfirmPassword())) {
            bindingResult.addError(new FieldError("passwordDto","confirmPassword","Mật khẩu xác nhận không đúng"));
        }
        if(!bindingResult.hasErrors()) {
            if(userService.changeNewPassword(key.get(),value.get(),passwordDto.getPassword())) {
                return "redirect:/account/login";
            }
            model.addAttribute("message","Tạo mới mật khẩu thất bại");
        }
        model.addAttribute("tokenKey",key.get());
        model.addAttribute("tokenValue",value.get());
        return "page/reset-password-page";
    }
}
