package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Query("select sum(pv.inventoryQuantity) from ProductVariant pv")
    Integer totalProductsInStock();

    @Query("SELECT p FROM ProductVariant p WHERE p.image.productSku like ?1")
    List<ProductVariant> findByProductSku(String sku);
}
