package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void save(Users users) {
        repository.save(users);
    }

    public Users get(String id) {
        Optional<Users> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(String id) {
        // CHECK POSITION NẾU LÀ OWNER và MANAGER THÌ CHO PHÉP XOÁ, NGƯỢC LẠI THÌ KHÔNG CHO
        repository.deleteById(id);
    }
}
