package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> listAll() {
        return repository.findAll();
    }

    public void save(Category category) {
        repository.save(category);
    }

    public Category get(Integer id) {
        Optional<Category> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
