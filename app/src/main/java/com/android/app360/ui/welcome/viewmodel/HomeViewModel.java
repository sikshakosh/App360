package com.android.app360.ui.welcome.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.appcompose.network.AppRepository;
import com.android.appcompose.network.model.ClassroomResponse;
import com.android.appcompose.network.model.MentorResponse;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ClassroomResponse> featuredClassrooms;
    private MutableLiveData<MentorResponse> featuredMentors;

    private AppRepository appRepository;

    public void init(){
        if(featuredClassrooms !=null){
            return;
        }

        appRepository = new AppRepository();
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
