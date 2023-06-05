package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Orders;
import com.chronos.chronosshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Orders> listAll() {
        // tạo câu @Query SELECT * FROM Order WHERE userId = '?';
        return null;
    }

    public void save(Orders order) {
        repository.save(order);

    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
