package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Cart;

import java.util.List;

public interface ICartService extends IService<Cart,Integer> {

    Cart findCartByProductColorIdAndUserId(int productColorId, String userId);

    List<Cart> deleteCartsByUser_UserId(String userId);
}
