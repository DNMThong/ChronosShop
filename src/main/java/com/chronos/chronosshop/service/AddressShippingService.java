package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.repository.AddressShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressShippingService {
    @Autowired
    private AddressShippingRepository repository;

    public List<AddressShipping> listAllByUserId() {
        // tạo câu @Query SELECT * FROM AddressShipping WHERE userId = '?';
        return null;
    }

    public void save(AddressShipping addressShipping) {
        repository.save(addressShipping);
    }

    public AddressShipping get(Integer id) {
        Optional<AddressShipping> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
