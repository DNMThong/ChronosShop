package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.repository.ProductVariantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantService implements IProductVariantService{
    private static final Logger logger = LoggerFactory.getLogger(ProductVariantService.class);
    @Autowired
    private ProductVariantRepository repository;


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
}
