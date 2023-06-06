package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.AddressShipping;
import com.chronos.chronosshop.entity.Admin;
import com.chronos.chronosshop.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService{
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private AdminRepository repository;


    @Override
    public boolean save(Admin admin) {
        try {
            repository.save(admin);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Admin admin){
        try {
        repository.save(admin);
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
    public List<Admin> findAll() {
        return repository.findAll();
    }

    @Override
    public Admin findById(String id) {
        Optional<Admin> admin = repository.findById(id);
        return admin.orElse(null);
    }
}
