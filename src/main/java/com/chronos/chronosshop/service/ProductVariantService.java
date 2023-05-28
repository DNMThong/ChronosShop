package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository repository;

    public List<ProductVariant> listAll() {
        // tạo câu @Query SELECT * FROM ProductVariant WHERE productId = '?';
        return null;
    }

    public void save(ProductVariant productVariant) {
        repository.save(productVariant);
    }

    public ProductVariant get(Integer id) {
        Optional<ProductVariant> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
