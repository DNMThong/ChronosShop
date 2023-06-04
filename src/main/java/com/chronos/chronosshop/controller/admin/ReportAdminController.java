package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/report")
public class ReportAdminController {
    @GetMapping("/invoice")
    public String invoiceReport(){
        return "page/admin/invoicereport";
    }
    @GetMapping("/purchaseorder")
    public String purchaseOrderReport(){
        return "page/admin/purchaseorderreport";
    }
}
