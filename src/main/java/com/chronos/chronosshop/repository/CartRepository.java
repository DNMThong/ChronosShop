package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findCartByProductVariant_ProductColorIdAndUser_UserId(int productColorId, String userId);
}
