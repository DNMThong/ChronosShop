package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAllAllOrderByUpdateTime() {
        return repository.findAllOrderByUpdateTime();
    }
    public List<Product> listAll() {
        return repository.findAll();
    }

    public void save(Product product) {
        try {
            repository.save(product);
        }catch (Exception e){
            System.out.println("xai con me no roi");
        }
    }

    public Product getOneProduct(Integer id) {
        Optional<Product> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public void updateStatusDeleted(Integer id){
            Product product = getOneProduct(id);
            if (product!=null){
                product.setStatus("Đã xóa");
                save(product);
            }else
                throw  new RuntimeException("Không tìm thấy sản phẩm với id: "+id);
    }
}
