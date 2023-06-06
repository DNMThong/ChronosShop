package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.repository.AddressShippingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressShippingService implements IAddressShippingService{
    private static final Logger logger = LoggerFactory.getLogger(AddressShippingService.class);
    @Autowired
    private AddressShippingRepository repository;

    @Override
    public boolean save(AddressShipping addressShipping) {
        try {
            repository.save(addressShipping);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(AddressShipping addressShipping) {
        try {
            repository.save(addressShipping);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
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
    public List<AddressShipping> findAll() {
        return repository.findAll();
    }

    @Override
    public AddressShipping findById(Integer id) {
        Optional<AddressShipping> addressShipping = repository.findById(id);
        return addressShipping.orElse(null);
    }
}
