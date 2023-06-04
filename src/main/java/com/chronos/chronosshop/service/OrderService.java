package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Orders;
import com.chronos.chronosshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Orders> listAll() {
        return repository.findAll();
    }

    public Orders get(String id) {
        Optional<Orders> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void save(Orders orders) {
        repository.save(orders);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
