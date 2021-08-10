package com.android.app360.ui.welcome.model;

public class ChildModel {
    private String category;

    public ChildModel(String movieCategory) {
        this.category = movieCategory;
    }
    public String itemCategory() {
        return category;
    }
}
