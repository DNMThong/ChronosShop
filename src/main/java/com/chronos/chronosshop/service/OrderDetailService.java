package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Feedback;
import com.chronos.chronosshop.entity.OrderDetail;
import com.chronos.chronosshop.repository.OrderDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService{
    private static final Logger logger = LoggerFactory.getLogger(IOrderDetailService.class);
    @Autowired
    private OrderDetailRepository repository;


    @Override
    public boolean save(OrderDetail orderDetail) {
        try {
            repository.save(orderDetail);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        try {
            repository.save(orderDetail);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrderDetail> findAll() {
        return repository.findAll();
    }

    @Override
    public OrderDetail findById(Long id) {
        Optional<OrderDetail> orderDetail = repository.findById(id);
        return orderDetail.orElse(null);
    }
}
