package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p ORDER BY p.createTime DESC")
    List<Product> findTop5ByOrderByCreateTimeDesc(Pageable pageable);
}
