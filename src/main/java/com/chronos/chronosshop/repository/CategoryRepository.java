package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c WHERE c.categoryParent IS NULL ORDER BY c.categoryId ASC")
    List<Category> listCategoryParent();

    @Query("SELECT c FROM Category c WHERE c.categoryParent IS NOT NULL ORDER BY c.categoryId ASC")
    List<Category> listCategoryChildren();

}
