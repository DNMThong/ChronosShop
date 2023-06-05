package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p order by p.updateTime desc ")
    List<Product> findAllOrderByUpdateTime();

    @Query("SELECT p FROM Product p ORDER BY p.createTime DESC")
    List<Product> findTop5ByOrderByCreateTimeDesc(Pageable pageable);

    @Query("SELECT p  FROM Product p WHERE MONTH(p.createTime) = MONTH(CURRENT_DATE) - 1 ORDER BY p.createTime DESC")
    List<Product> findNewestProducts();

    @Query("SELECT p  FROM Product p WHERE p.productName LIKE CONCAT('%', ?1 ,'%')")
    List<Product> findProductsByName(String cond);
}
