package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository repository;

    public List<Category> findCategoriesByName(String name) {
        return repository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public boolean save(Category category) {
        try {
            repository.save(category);
            repository.flush();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        try {
            repository.save(category);
            repository.flush();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            repository.deleteById(id);
            repository.flush();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> category = repository.findById(id);
        return category.orElse(null);
    }

    @Override
    public List<Product> findProductsBySlug(String slug) {
        Optional<Category> optional = repository.findByCategoryUrlAndSubcategoryIsNull(slug);
        if(optional.isPresent()) {
            return optional.get().getProducts();
        }
        return null;
    }

    @Override
    public Category findBySlug(String slug) {
        Optional<Category> optional = repository.findByCategoryUrlAndSubcategoryIsNull(slug);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
