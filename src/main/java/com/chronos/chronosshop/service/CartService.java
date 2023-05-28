package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Cart;
import com.chronos.chronosshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;

    public List<Cart> listAllByUserId() {
        // tạo câu @Query SELECT * FROM Cart WHERE userId = '?';
        return repository.findAll();
    }

    public void save(Cart cart) {
        // LÀM THẾ NÀO ĐÓ ĐỂ KHI NGƯỜI DÙNG TĂNG SỐ LƯỢNG LÊN THÌ SẼ CẬP NHẬT SỐ LƯỢNG VÀO TRONG DATABASE
        repository.save(cart);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
