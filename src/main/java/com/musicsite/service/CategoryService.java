package com.musicsite.service;

import com.musicsite.entity.Category;
import com.musicsite.repository.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoriesSaute() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(c -> Hibernate.initialize(c.getTracks()));
        categories.forEach(c -> Hibernate.initialize(c.getAlbums()));
        return categories;
    }

    public List<Category> getActiveCategories() {
        List<Category> activeCategories =
                getCategories()
                        .stream()
                        .filter(c -> c.getAlbums().size() != 0 || c.getTracks().size() != 0)
                        .collect(Collectors.toList());
        if (activeCategories.size() == 0)
            return null;
        else
            return activeCategories;
    }

    public void save (Category category) {
        categoryRepository.save(category);
    }

    public Category getCategoryByName (String name) {
        return categoryRepository.getFirstCategoryByNameIgnoreCase(name);
    }

}
