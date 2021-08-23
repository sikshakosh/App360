package com.android.app360.ui.welcome.model;

import com.android.app360.common.constants.DataType;

import java.util.ArrayList;

public class ParentModel {
    private String itemCategory;
    private ArrayList<Object> data;
    private DataType type;


    public ParentModel(String category,DataType type) {
        this.itemCategory = category;
        this.type = type;
        data = new ArrayList<Object>();

    }


    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
}
