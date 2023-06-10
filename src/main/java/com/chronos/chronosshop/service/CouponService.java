package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Coupon;
import com.chronos.chronosshop.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService implements ICouponService {
    private static final Logger logger = LoggerFactory.getLogger(ICouponService.class);
    @Autowired
    private CouponRepository repository;

    @Override
    public boolean save(Coupon coupon) {
        try {
            repository.save(coupon);
            repository.flush();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Coupon coupon) {
        try {
            repository.save(coupon);
            repository.flush();
            return true;
        } catch (Exception e) {
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Coupon> findAll() {
        return repository.findAll();
    }

    @Override
    public Coupon findById(String id) {
        Optional<Coupon> Coupon = repository.findById(id);
        return Coupon.orElse(null);
    }
}
