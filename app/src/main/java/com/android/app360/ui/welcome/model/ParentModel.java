package com.android.app360.ui.welcome.model;

import com.android.appcompose.network.model.Classroom;
import com.android.appcompose.network.model.Mentor;

import java.util.ArrayList;
import java.util.List;

public class ParentModel {
    private String itemCategory;
    private ArrayList<Classroom> classroomArray;
    private ArrayList<Mentor> mentorArray;


    public ParentModel(String category) {
        this.itemCategory = category;
        classroomArray = new ArrayList<Classroom>();
        mentorArray = new ArrayList<Mentor>();
    }


    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ArrayList<Classroom> getClassroomArray() {
        return classroomArray;
    }

    public void setClassroomArray(ArrayList<Classroom> classroomArray) {
        this.classroomArray = classroomArray;
    }

    public ArrayList<Mentor> getMentorArray() {
        return mentorArray;
    }

    public void setMentorArray(ArrayList<Mentor> mentorArray) {
        this.mentorArray = mentorArray;
    }
}
