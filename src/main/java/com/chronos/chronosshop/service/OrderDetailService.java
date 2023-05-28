package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.OrderDetail;
import com.chronos.chronosshop.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repository;

    public List<OrderDetail> listAll() {
        // tạo câu @Query SELECT * FROM OrderDetail WHERE orderId = '?';
        return null;
    }

    public void save(OrderDetail orderDetail) {
        repository.save(orderDetail);
    }
}
