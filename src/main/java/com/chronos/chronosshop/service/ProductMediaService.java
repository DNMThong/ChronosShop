package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.ProductMedia;
import com.chronos.chronosshop.repository.ProductMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMediaService {
    @Autowired
    private ProductMediaRepository repository;

    public List<ProductMedia> listAllByProductId() {
        // tạo câu @Query SELECT * FROM ProductMedia WHERE productId = '?';
        return null;
    }

    public void save(ProductMedia productMedia) {
        repository.save(productMedia);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
