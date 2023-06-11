package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Cart;

public interface ICartService extends IService<Cart,Integer> {

    Cart findCartByProductColorIdAndUserId(int productColorId, String userId);
}
