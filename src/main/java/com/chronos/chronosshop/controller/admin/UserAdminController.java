package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    @Autowired
    UserService userService;
    public Date date = Calendar.getInstance().getTime();
    public java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new Users());
        return "page/admin/adduser";
    }

    @PostMapping("/add")
    public String saveAddUser(Users user, RedirectAttributes redirectAttributes) {
        user.setCreatedDate(sqlDate);
        user.setUpdatedDate(sqlDate);
        user.setLastLogin(sqlDate);
        userService.saveOrUpdate(user);
        redirectAttributes.addFlashAttribute("message", "Thêm người dùng thành công!");
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("show", true);
        return "redirect:/admin/users/list";
    }


    @GetMapping("/list")
    public String getAllUser(Model model) {
        model.addAttribute("listUser", userService.listAll());
        return "page/admin/userlists";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String id,  Model model, Users user , RedirectAttributes redirectAttributes) {
        model.addAttribute("user", userService.findUserById(id));
        return "page/admin/adduser";
    }
    @PostMapping("/edit/{id}")
    public String saveUpdate(@PathVariable("id") String id,Users user, RedirectAttributes redirectAttributes) {
        Users u = userService.findUserById(id);
        user.setCreatedDate(u.getCreatedDate());
        user.setUpdatedDate(sqlDate);
        user.setLastLogin(u.getLastLogin());
        userService.saveOrUpdate(user);
        redirectAttributes.addFlashAttribute("message", "Sửa người dùng thành công!");
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("show", true);
        return "redirect:/admin/users/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        if (!userService.delete(id)) {
            redirectAttributes.addFlashAttribute("message", "Xóa người dùng thành công");
            redirectAttributes.addFlashAttribute("type", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Xóa người dùng thất bại");
            redirectAttributes.addFlashAttribute("type", "error");
        }
        redirectAttributes.addFlashAttribute("show", true);
        return "redirect:/admin/users/list";
    }

    @ModelAttribute("status_list")
    public List<String> getStatus() {
        return Arrays.asList("Hoạt động", "Bị khóa", "Khác");
    }
}
