package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository  categoryRepository;

    @Autowired
    private CategoryRepository repository;

    public List<Category> listAll() {
        return repository.findAll();
    }

    public boolean save(Category category) {
        try {
            repository.save(category);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }
    public List<Category> getAll() {
        return categoryRepository.findAll();
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

    public List<Category> listCategoryParent() {
        return repository.findCategoryParent();
    }
    public List<Category> findCategoriesByName(String name) {
        return categoryRepository.findByNameContainsIgnoreCase(name);
    }
}
