package com.android.app360.ui.welcome.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.appcompose.database.UserClassroom;
import com.android.appcompose.network.AppRepository;
import com.android.appcompose.network.model.ClassroomResponse;
import com.android.appcompose.network.model.MentorResponse;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ClassroomResponse> featuredClassrooms;
    private MutableLiveData<MentorResponse> featuredMentors;

    private AppRepository appRepository;

    public void init(Context context){
        if(featuredClassrooms !=null){
            return;
        }

        appRepository = new AppRepository(context);
        appRepository.saveUserClassroom(new UserClassroom(1, "name","chash", "admiin", "data", "members", "String invites", true, 123456, 1234567));
        featuredClassrooms = appRepository.getFeaturedClassrooms();
        featuredMentors = appRepository.getFeaturedMentors();
    }

    public LiveData<ClassroomResponse> getFeaturedClassroomsRepository(){
        return featuredClassrooms;
    }

    public LiveData<MentorResponse> getFeaturedMentorsRepository(){
        return featuredMentors;
    }






}
