package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Admin;
import com.chronos.chronosshop.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    public List<Admin> listAll() {
        return repository.findAll();
    }

    public void save(Admin admin) {
        repository.save(admin);
    }

    public Admin get(String id) {
        Optional<Admin> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(String id) {
        // CHECK POSITION NẾU LÀ OWNER THÌ CHO PHÉP XOÁ, NGƯỢC LẠI THÌ KHÔNG CHO
        repository.deleteById(id);
    }
}
