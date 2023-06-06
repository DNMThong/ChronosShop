package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Admin;
import com.chronos.chronosshop.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService{
    @Autowired
    private AdminRepository repository;


    @Override
    public boolean save(Admin admin) {
        return false;
    }

    @Override
    public boolean update(Admin admin) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public Admin findById(String s) {
        return null;
    }
}
