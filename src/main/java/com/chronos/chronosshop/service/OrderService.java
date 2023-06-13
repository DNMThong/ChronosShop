package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.entity.Orders;
import com.chronos.chronosshop.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository repository;


    @Override
    public boolean save(Orders orders) {
        try {
            repository.save(orders);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Orders orders) {
        try {
            repository.save(orders);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
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
    public List<Orders> findAll() {
        return repository.findAll();
    }

    @Override
    public Orders findById(String id) {
        Optional<Orders> order = repository.findById(id);
        return order.orElse(null);
    }

    @Override
    public Orders createOrderAndPayment(String userId, int shipId, String couponId, String status, LocalDateTime createTime, LocalDateTime updateTime, String paymentMethod, Long subTotal, Long subTotalFee, Long total, String currency) {
        return repository.taoOrderVaPayment(userId, shipId, couponId, status, createTime, updateTime, paymentMethod, subTotal, subTotalFee, total, currency);
    }

    @Override
    public Orders findByUser_UserIdAndAddressShipping_ShipIdAndCreateTime(String userId, int shipId, LocalDateTime createDate) {
        return repository.findByUser_UserIdAndAddressShipping_ShipIdAndCreateTime(userId, shipId, createDate);
    }


}
