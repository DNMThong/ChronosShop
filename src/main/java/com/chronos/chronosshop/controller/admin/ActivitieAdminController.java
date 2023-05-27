package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class ActivitieAdminController {
    @GetMapping("activities")
    public String activities(){
        return "admin/activities";
    }

}
