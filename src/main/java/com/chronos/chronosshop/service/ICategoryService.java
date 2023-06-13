package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.entity.Product;

import java.util.List;

public interface ICategoryService extends IService<Category,Integer>{
    public List<Product> findProductsBySlug(String slug);

    public Category findBySlug(String slug);
}
