package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findCartByProductVariant_ProductColorIdAndUser_UserId(int productColorId, String userId);

    List<Cart> deleteCartsByUser_UserId(String userId);
}
