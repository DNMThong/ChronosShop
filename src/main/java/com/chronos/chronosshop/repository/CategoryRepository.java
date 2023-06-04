package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c WHERE c.categoryParent IS NULL ORDER BY c.categoryId ASC")
    List<Category> listCategoryParent();

    @Query("SELECT c FROM Category c WHERE c.categoryParent IS NOT NULL ORDER BY c.categoryId ASC")
    List<Category> listCategoryChildren();

    @Query("select c from Category c where upper(c.categoryName) like upper(concat('%', ?1, '%'))")
    List<Category> findByNameContainsIgnoreCase(@Nullable String name);

    @Query("select c from Category c where upper(c.categoryName) like upper(?1)")
    Optional<Category> findByNameLikeIgnoreCase(@Nullable String name);

}
