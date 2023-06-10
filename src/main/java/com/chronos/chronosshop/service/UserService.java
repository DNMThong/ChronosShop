package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService{
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;


    public Users getUserByEmail(String email) {
        return  repository.getUserByEmail(email);
    }

    @Override
    public boolean save(Users user) {
        try {
            UUID uuid = UUID.randomUUID();
            String randomId = uuid.toString().replaceAll("-","").substring(0,25);
            user.setUserId(randomId);
            user.setStatus("Hoạt động");
            user.setPassword(passwordEncode.encode(user.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            repository.save(user);
            repository.flush();
            return true;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Users user) {
        try {
            user.setPassword(passwordEncode.encode(user.getPassword()));
            user.setUpdatedDate(LocalDateTime.now());
            repository.save(user);
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
            Users user = findById(id);
            user.setStatus("Bị khóa");
            update(user);
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

    @Override
    public boolean saveUserFromGoogle(Users user) {
        Optional<Users> users = repository.findById(user.getUserId());
        if(users.isPresent()) {
            return false;
        }
        user.setStatus("Hoạt động");
        user.setCreatedDate(LocalDateTime.now());
        repository.save(user);
        repository.flush();
        return true;
    }
}
