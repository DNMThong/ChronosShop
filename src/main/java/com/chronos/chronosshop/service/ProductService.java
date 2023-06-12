package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.Payment;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.repository.ProductRepository;
import com.chronos.chronosshop.util.Delete;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository repository;


    public void updateStatusDeleted(Integer id){
            Product product = findById(id);
            if (product!=null){
                product.setDeleted(Delete.deleted);
                save(product);
            }else
                throw  new RuntimeException("Không tìm thấy sản phẩm với id: "+id);
    }

    public List<Product> getListNewestProduct(Integer limit) {
        return repository.findNewestProducts().subList(0, limit);
    }

    public List<Product> getListProductContainName(String name) {
        return repository.findProductsByName(name);
    }

    @Override
    public boolean save(Product product) {
        try {
            Product product1 = findById(product.getProductId());
            if(product1!=null){
                product1.setUpdateTime(LocalDateTime.now());
                repository.save(product1);
            }else {
                product.setDeleted(Delete.notDeleted);
                product.setCreateTime(LocalDateTime.now());
                product.setUpdateTime(LocalDateTime.now());
                repository.save(product);
            }
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            product.setUpdateTime(LocalDateTime.now());
            repository.save(product);
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
    public List<Product> findAll() {
        return repository.findProductsNotDeleted();
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> product = repository.findById(id);
        return product.orElse(null);
    }
}
