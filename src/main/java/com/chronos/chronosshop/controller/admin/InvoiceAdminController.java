package com.chronos.chronosshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class InvoiceAdminController {
    @GetMapping("invoicereport")
    public String invoiceReport(){
        return "admin/invoicereport";
    }
    @GetMapping("purchaseorderreport")
    public String purchaseOrderReport(){
        return "admin/purchaseorderreport";
    }
}
