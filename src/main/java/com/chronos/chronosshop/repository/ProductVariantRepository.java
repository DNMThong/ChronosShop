package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Query("select sum(pv.inventoryQuantity) from ProductVariant pv")
    Integer totalProductsInStock();
}
