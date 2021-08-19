package com.android.app360.ui.welcome.model;

import com.android.appcompose.database.UserClassroom;
import com.android.appcompose.network.model.Classroom;
import com.android.appcompose.network.model.Mentor;

import java.util.ArrayList;
import java.util.List;

public class ParentModel {
    private String itemCategory;
    private ArrayList<Object> data;



    public ParentModel(String category) {
        this.itemCategory = category;
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
}
