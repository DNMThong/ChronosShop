package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.ProductMedia;
import com.chronos.chronosshop.repository.ProductMediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMediaService implements IProductMediaService{
    private static final Logger logger = LoggerFactory.getLogger(ProductMediaService.class);
    @Autowired
    private ProductMediaRepository repository;


    @Override
    public boolean save(ProductMedia productMedia) {
        try {
            repository.save(productMedia);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(ProductMedia productMedia) {
        try {
            repository.save(productMedia);
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
    public List<ProductMedia> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductMedia findById(Integer id) {
        Optional<ProductMedia> productMedia = repository.findById(id);
        return productMedia.orElse(null);
    }
}
