package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("select c from Category c where upper(c.name) like upper(concat('%', ?1, '%'))")
    List<Category> findByNameContainsIgnoreCase(@Nullable String name);
    @Query("select c from Category c where upper(c.name) like upper(?1)")
    Optional<Category> findByNameLikeIgnoreCase(@Nullable String name);
}