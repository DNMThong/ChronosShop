package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.repository.ProductVariantRepository;
import com.chronos.chronosshop.util.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantService implements IProductVariantService{
    private static final Logger logger = LoggerFactory.getLogger(ProductVariantService.class);
    @Autowired
    private ProductVariantRepository repository;
    @Autowired
    private  ProductService productService;
    @Autowired
    private  ImageService imageService;


    @Override
    public boolean save(ProductVariant productVariant) {
        try {
            repository.save(productVariant);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(ProductVariant productVariant) {
        try {
            repository.save(productVariant);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            repository.deleteById(id);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<ProductVariant> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductVariant findById(Integer id) {
        Optional<ProductVariant> productVariant = repository.findById(id);
        return productVariant.orElse(null);
    }


    public void saveProcedure(ProductVariant productVariant, Integer productId, String sku,String image1){
        try {
            Optional<ProductVariant> productVariant1 =repository.findById(productVariant.getProductColorId());
            Image image = imageService.findById(sku);
            if (productVariant1.isPresent() && image!=null){
                productVariant.setProduct(productService.findById(productId));
                productVariant.setImage(image);
                productVariant.setImage1(image1);
                repository.updateProductVariantImage1(image1,sku);
                productVariant.setCreateTime(LocalDateTime.now());
                image.setImage1(image1);
                imageService.save(image);
                update(productVariant);
                System.out.println("update thanh cong");
            }else {
                repository.TaoProductSkuVaImage(productId,sku,productVariant.getProductColorName(), productVariant.getProductSize(), productVariant.getInventoryQuantity(),image1, Date.valueOf(LocalDate.now()));
                System.out.println("Tao thanh cong");
            }
            repository.flush();
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
