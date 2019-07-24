package com.musicsite.category;

import com.musicsite.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getFirstCategoryByNameIgnoreCase(String name);
}
