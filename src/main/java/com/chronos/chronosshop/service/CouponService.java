package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Coupon;
import com.chronos.chronosshop.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService {
    @Autowired
    private CouponRepository repository;

    public List<Coupon> listAll() {
        return repository.findAll();
    }

    public void save(Coupon coupon) {
        repository.save(coupon);
    }

    public Coupon get(String id) {
        Optional<Coupon> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
