package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Admin;
import com.chronos.chronosshop.entity.Cart;
import com.chronos.chronosshop.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements  ICartService{
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    @Autowired
    private CartRepository repository;


    @Override
    public boolean save(Cart cart) {
        try {
            repository.save(cart);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Cart cart) {
        try {
            repository.save(cart);
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
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public Cart findById(Integer id) {
        Optional<Cart> cart = repository.findById(id);
        return cart.orElse(null);
    }

    @Override
    public Cart findCartByProductColorIdAndUserId(int productColorId, String userId) {
        return repository.findCartByProductVariant_ProductColorIdAndUser_UserId(productColorId, userId);
    }

    @Override
    public List<Cart> deleteCartsByUser_UserId(String userId) {
        return repository.deleteCartsByUser_UserId(userId);
    }


}
