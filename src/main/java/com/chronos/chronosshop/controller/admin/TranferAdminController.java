package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class TranferAdminController {
    @GetMapping("addtransfer")
    public String addTransfer(){
        return "admin/addtransfer";
    }
    @GetMapping("transferlist")
    public String transferList() {
        return "admin/transferlist";
    }
}
