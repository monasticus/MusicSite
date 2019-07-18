package com.musicsite.entity;

import java.util.ArrayList;
import java.util.List;

public class CategorySelector {

    private List<Category> categoryList = new ArrayList<>();

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
