package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Payment;
import com.chronos.chronosshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public void save(Payment payment) {
        repository.save(payment);
    }

    public Payment get(String id) {
        Optional<Payment> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
}
