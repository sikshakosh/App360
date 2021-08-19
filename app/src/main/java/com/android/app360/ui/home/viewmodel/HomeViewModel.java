package com.android.app360.ui.home.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.appcompose.network.AppRepository;
import com.android.appcompose.network.model.ClassroomResponse;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ClassroomResponse> mutableLiveFClassrooms;

    private AppRepository appRepository;

    public void init(Context context){
        if(mutableLiveFClassrooms!=null){
            return;
        }
        //appRepository = new AppRepository(context);
       // mutableLiveFClassrooms = appRepository.getFeaturedClassrooms();

    }

    public LiveData<ClassroomResponse> getFClassroomsRepository(){
        return mutableLiveFClassrooms;
    }


}
