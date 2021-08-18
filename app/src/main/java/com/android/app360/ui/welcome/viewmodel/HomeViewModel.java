package com.android.app360.ui.welcome.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.appcompose.database.UserClassroom;
import com.android.appcompose.network.AppRepository;
import com.android.appcompose.network.model.ClassroomResponse;
import com.android.appcompose.network.model.MentorResponse;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private LiveData<List<UserClassroom>> featuredClassrooms;
    private MutableLiveData<MentorResponse> featuredMentors;

    private AppRepository appRepository;

    public HomeViewModel(Application application){
        super(application);
        if(featuredClassrooms !=null){
            return;
        }

        appRepository = new AppRepository(application);
        featuredClassrooms = appRepository.getLocalClassrooms();
       // featuredMentors = appRepository.getFeaturedMentors();
    }

    public LiveData<List<UserClassroom>> getLocalClassrooms(){
        return featuredClassrooms;
    }

    public void getRemoteClassrooms(){
        appRepository.getRemoteClassrooms();
    }

    public LiveData<MentorResponse> getFeaturedMentorsRepository(){
        return featuredMentors;
    }






}