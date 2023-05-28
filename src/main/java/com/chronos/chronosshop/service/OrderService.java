package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.Order;
import com.chronos.chronosshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> listAll() {
        // tạo câu @Query SELECT * FROM Order WHERE userId = '?';
        return null;
    }

    public void save(Order order) {
        repository.save(order);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
