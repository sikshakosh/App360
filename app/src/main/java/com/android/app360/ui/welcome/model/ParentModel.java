package com.android.app360.ui.welcome.model;

import com.android.appcompose.database.UserClassroom;
import com.android.appcompose.network.model.Classroom;
import com.android.appcompose.network.model.Mentor;

import java.util.ArrayList;
import java.util.List;

public class ParentModel {
    private String itemCategory;
    private ArrayList<UserClassroom> classroomArray;
    private ArrayList<Mentor> mentorArray;


    public ParentModel(String category) {
        this.itemCategory = category;
        classroomArray = new ArrayList<UserClassroom>();
        mentorArray = new ArrayList<Mentor>();
    }


    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ArrayList<UserClassroom> getClassroomArray() {
        return classroomArray;
    }

    public void setClassroomArray(ArrayList<UserClassroom> classroomArray) {
        this.classroomArray = classroomArray;
    }

    public ArrayList<Mentor> getMentorArray() {
        return mentorArray;
    }

    public void setMentorArray(ArrayList<Mentor> mentorArray) {
        this.mentorArray = mentorArray;
    }
}
