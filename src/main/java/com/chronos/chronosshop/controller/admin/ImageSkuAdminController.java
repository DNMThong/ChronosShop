package com.chronos.chronosshop.controller.admin;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.repository.ImageRepository;
import com.chronos.chronosshop.repository.ProductVariantRepository;
import com.chronos.chronosshop.service.IStorageService;
import com.chronos.chronosshop.service.ImageService;
import com.chronos.chronosshop.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/image/")
public class ImageSkuAdminController {
    @Autowired
    ImageService imageService;
    @Autowired
    ProductVariantRepository productVariantRepository;
    @Autowired
    private IStorageService storageService;

    // khong fill duoc do xac dinh moi quan he giua bang image va productVariant
    // muon xem thi xoa OnetoOne cua bang image di
    @GetMapping("list")
    public String variantList(Model model) {
        model.addAttribute("imageSku", imageService.findAll());
        return "page/admin/image-list";
    }

    @GetMapping(value = {"add", "add/{sku}"})
    public String imageAddGet(Model model, @PathVariable(value = "sku", required = false) String sku) {
        Image image = imageService.findById(sku);
        model.addAttribute("imagess", image);
        model.addAttribute("title", "Thêm");
        return "page/admin/addImage";
    }
    @PostMapping("/add")
    public String imageSave(Image image, @RequestParam(name = "image11") MultipartFile image1,
                            @RequestParam(name = "image12") MultipartFile image2,
                            @RequestParam(name = "image13") MultipartFile image3,
                            @RequestParam(name = "image14") MultipartFile image4,
                            @RequestParam(name = "image15") MultipartFile image5,
                            @RequestParam(name = "image16") MultipartFile image6
            ,@RequestParam(value = "image1Link", required = false, defaultValue = "") String image1Link
            , @RequestParam(value = "image2Link", required = false, defaultValue = "") String image2Link
            , @RequestParam(value = "image3Link", required = false, defaultValue = "") String image3Link
            , @RequestParam(value = "image4Link", required = false, defaultValue = "") String image4Link
            , @RequestParam(value = "image5Link", required = false, defaultValue = "") String image5Link
            , @RequestParam(value = "image6Link", required = false, defaultValue = "") String image6Link
            , RedirectAttributes redirectAttributes) {
        String generatedFileName1;
        String generatedFileName2;
        String generatedFileName3;
        String generatedFileName4;
        String generatedFileName5;
        String generatedFileName6;

        if (!image1.isEmpty()) {
            generatedFileName1 = storageService.storeFile(image1);
        } else {
            if (image1Link.isEmpty()) {
                generatedFileName1 = "";
            } else {
                generatedFileName1 = image1Link;
            }
        }
        if (!image2.isEmpty()) {
            generatedFileName2 = storageService.storeFile(image2);
        } else {
            if (image2Link.isEmpty()) {
                generatedFileName2 = "";
            } else {
                generatedFileName2 = image2Link;
            }
        }
        if (!image3.isEmpty()) {
            generatedFileName3 = storageService.storeFile(image3);
        } else {
            if (image3Link.isEmpty()) {
                generatedFileName3 = "";
            } else {
                generatedFileName3 = image3Link;
            }
        }
        if (!image4.isEmpty()) {
            generatedFileName4 = storageService.storeFile(image4);
        } else {
            if (image4Link.isEmpty()) {
                generatedFileName4 = "";
            } else {
                generatedFileName4 = image4Link;
            }
        }
        if (!image5.isEmpty()) {
            generatedFileName5 = storageService.storeFile(image5);
        } else {
            if (image5Link.isEmpty()) {
                generatedFileName5 = "";
            } else {
                generatedFileName5 = image5Link;
            }
        }
        if (!image6.isEmpty()) {
            generatedFileName6 = storageService.storeFile(image6);
        } else {
            if (image3Link.isEmpty()) {
                generatedFileName6 = "";
            } else {
                generatedFileName6 = image6Link;
            }
        }
            image.setImage1(generatedFileName1);
            image.setImage2(generatedFileName2);
            image.setImage3(generatedFileName3);
            image.setImage4(generatedFileName4);
            image.setImage5(generatedFileName5);
            image.setImage6(generatedFileName6);
            productVariantRepository.updateProductVariantImage1(generatedFileName1,image.getProductSku());
            imageService.save(image);
        redirectAttributes.addFlashAttribute("message", "Lưu sản phẩm thành công!");
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("show", true);
        return "redirect:/admin/product-variant/list";
    }


}
