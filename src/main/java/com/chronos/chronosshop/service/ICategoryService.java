package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Category;

import java.util.List;

public interface ICategoryService extends IService<Category,Integer>{

    List<Category> findCategoriesByName(String name);
}
