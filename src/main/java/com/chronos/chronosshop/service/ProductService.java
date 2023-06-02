package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> listAll() {
        // tạo câu @Query SELECT * FROM Product ORDER BY createTime DESC;
        return  repository.findAll();
    }

    public void save(Product product) {
        repository.save(product);
    }

    public Product get(Integer id) {
        Optional<Product> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
