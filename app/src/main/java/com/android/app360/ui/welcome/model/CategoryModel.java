package com.android.app360.ui.welcome.model;

public class CategoryModel {
    private String category;

    public CategoryModel(String movieCategory) {
        this.category = movieCategory;
    }
    public String itemCategory() {
        return category;
    }
}
