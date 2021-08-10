package com.android.app360.ui.welcome.model;

import com.android.appcompose.network.Classroom;

import java.util.ArrayList;
import java.util.List;

public class ParentModel {
    private String itemCategory;
    private ArrayList<Classroom> childArray;

    public ParentModel(String category, ArrayList<Classroom> childArray) {
        this.itemCategory = category;
        this.childArray =  childArray;
    }


    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ArrayList<Classroom> getChildArray() {
        return childArray;
    }

    public void setChildArray(List<Classroom> childArray) {
        this.childArray.addAll(childArray);
    }
}
