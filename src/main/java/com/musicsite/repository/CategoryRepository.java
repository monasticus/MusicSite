package com.musicsite.repository;

import com.musicsite.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getFirstCategoryByNameIgnoreCase(String name);
    List<Category> getCategories();
}
