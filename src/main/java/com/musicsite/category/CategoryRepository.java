package com.musicsite.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getFirstCategoryByNameIgnoreCase(String name);
}
