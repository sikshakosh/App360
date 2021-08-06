package com.android.app360.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.appcompose.network.AppRepository;
import com.android.appcompose.network.Classroom;
import com.android.appcompose.network.ClassroomResponse;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ClassroomResponse> mutableLiveFClassrooms;
    private AppRepository appRepository;

    public void init(){
        if(mutableLiveFClassrooms!=null){
            return;
        }
        appRepository = new AppRepository();
        mutableLiveFClassrooms = appRepository.getFeaturedClassrooms();
    }

    public LiveData<ClassroomResponse> getFClassroomsRepository(){
        return mutableLiveFClassrooms;
    }


}
