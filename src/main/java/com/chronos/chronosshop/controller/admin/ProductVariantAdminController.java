package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.service.IStorageService;
import com.chronos.chronosshop.service.ProductService;
import com.chronos.chronosshop.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product-variant/")
public class ProductVariantAdminController {
    @Autowired
    private IStorageService storageService;
    @Autowired
    ProductVariantService productVariantService;
    @Autowired
    ProductService productService;
    @GetMapping("list")
    public String variantList(Model model){
         model.addAttribute("productVariants", productVariantService.findAll());
        return"page/admin/product-variant-list";
    }
    @GetMapping(value = {"add","add/{id}"})
    public String variantAddGet(Model model,@PathVariable(value ="id", required = false) Integer productId){
            model.addAttribute("productId",productId);
            model.addAttribute("productVariants",new ProductVariant());
            model.addAttribute("image11","");
        model.addAttribute("title","Thêm");
        return"page/admin/addproduct-variant";
    }
    @PostMapping("/add")
    public String variantSave(ProductVariant productVariant, @RequestParam("productId") Integer producId, @RequestParam("sku") String sku, @RequestParam(name = "image11") MultipartFile image1,@RequestParam(value = "image1Link",required = false,defaultValue = "")String image1Link, RedirectAttributes redirectAttributes) {
            String generatedFileName;
            if(!image1.isEmpty()){
                generatedFileName = storageService.storeFile(image1);
            }else {
                if(image1Link.isEmpty()){
                    generatedFileName="";
                }else {
                    generatedFileName=image1Link;
                }
            }
        productVariantService.saveProcedure(productVariant,producId,sku,generatedFileName);
        redirectAttributes.addFlashAttribute("message","Lưu sản phẩm thành công!");
        redirectAttributes.addFlashAttribute("type","success");
        redirectAttributes.addFlashAttribute("show",true);
        return "redirect:/admin/product-variant/list";
    }
    @GetMapping("/edit/{id}")
    public String variantUpdate(@PathVariable("id") Integer id,Model model,RedirectAttributes redirectAttributes) {
        ProductVariant productVariant = productVariantService.findById(id);
        if (productVariant!=null){
            model.addAttribute("productVariants",productVariant);
            model.addAttribute("productId",productVariant.getProduct().getProductId());
            model.addAttribute("sku",productVariant.getImage().getProductSku());
            model.addAttribute("image11",productVariant.getImage1());
            model.addAttribute("image1Link",productVariant.getImage1());
            model.addAttribute("title","Cập nhật");
            return "page/admin/addproduct-variant";
        }
        redirectAttributes.addFlashAttribute("message","Không tìm thấy sản phẩm");
        return "redirect:/admin/product-variant/list";
    }
    @ModelAttribute("sizeProduct")
    public List<String> sizeProduct(){
        return Arrays.asList("S","M","L","XL","2XL","3XL","XXL");
    }

}
