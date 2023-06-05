package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Image;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<Users> listAll() {
        // tạo câu @Query SELECT * FROM User;
        return repository.findAll();
    }

    public boolean saveOrUpdate(Users user) {
        repository.save(user);
        return repository.findById(user.getUserId()).isPresent();
    }
    public Users findUserById(String id) {
        return repository.findById(id).get();
    }
    public Users get(String id) {
        Optional<Users> result = repository.findById(id);
        return result.orElse(null);
    }

    public boolean delete(String id) {
        // CHECK POSITION NẾU LÀ OWNER và MANAGER THÌ CHO PHÉP XOÁ, NGƯỢC LẠI THÌ KHÔNG CHO
        repository.deleteById(id);
        return repository.findById(id).isPresent();
    }

}
