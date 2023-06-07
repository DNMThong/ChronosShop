package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository repository;


    public Users getUserByEmail(String email) {
        return  repository.getUserByEmail(email);
    }

    @Override
    public boolean save(Users users) {
        try {
            repository.save(users);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Users users) {
        try {
            repository.save(users);
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
    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public Users findById(String id) {
        Optional<Users> users = repository.findById(id);
        return users.orElse(null);
    }
}
