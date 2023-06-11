package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    @Autowired
    UserService userService;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @GetMapping("")
    public String getAllUser(Model model) {
        model.addAttribute("listUser", userService.findAll());
        return "page/admin/userlists";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("user") Users user) {
        return "page/admin/adduser";
    }


    @PostMapping("/add")
    public String saveAddUser(@ModelAttribute("user") Users user,
                              RedirectAttributes redirectAttributes) {
        if(userService.save(user)) {
            redirectAttributes.addFlashAttribute("message", "Thêm người dùng thành công!");
            redirectAttributes.addFlashAttribute("type", "success");
            redirectAttributes.addFlashAttribute("show", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "Thêm người dùng thất bại!");
            redirectAttributes.addFlashAttribute("type", "error");
            redirectAttributes.addFlashAttribute("show", true);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String id, Users user,
                           Model model) {
        model.addAttribute("user", userService.findById(id));
        return "page/admin/adduser";
    }

    @PostMapping("/edit/{id}")
    public String saveUpdate(@PathVariable("id") String id, Users user,
                             RedirectAttributes redirectAttributes) {
        user.setUserId(id);
        if(userService.update(user)) {
            redirectAttributes.addFlashAttribute("message", "Sửa người dùng thành công!");
            redirectAttributes.addFlashAttribute("type", "success");
            redirectAttributes.addFlashAttribute("show", true);
            return "redirect:/admin/users";
        } else {
            redirectAttributes.addFlashAttribute("message", "Sửa người dùng thất bại!");
            redirectAttributes.addFlashAttribute("type", "error");
            redirectAttributes.addFlashAttribute("show", true);
            return "redirect:/admin/users/add";
        }

    }


    @RequestMapping("/delete/{id}")
    public String saveDeleteUser(@PathVariable("id") String id,
                                 RedirectAttributes redirectAttributes) {
        if (userService.delete(id)) {
            redirectAttributes.addFlashAttribute("message", "Xóa người dùng thành công");
            redirectAttributes.addFlashAttribute("type", "success");
            redirectAttributes.addFlashAttribute("show", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "Xóa người dùng thất bại");
            redirectAttributes.addFlashAttribute("type", "error");
            redirectAttributes.addFlashAttribute("show", true);
        }
        return "redirect:/admin/users";
    }

    @ModelAttribute("status_list")
    public List<String> getStatus() {
        return Arrays.asList("Hoạt động", "Bị khóa");
    }
}
