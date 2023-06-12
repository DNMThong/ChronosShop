package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Procedure
    void TaoProductSkuVaImage(Integer id, String sku, String colorName, String size, Integer inventoryQuantity, String image1, Date crateTime);
    @Modifying
    @Transactional
    @Query(value = "UPDATE product_variant SET image1 = ?1  WHERE product_sku = ?2 ", nativeQuery = true)
    void updateProductVariantImage1(String image1, String product_sku);
}
