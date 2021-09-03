package com.android.app360.ui.welcome.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.appcompose.composable.utility.cardgrid.OnCardGridItemClickListener;
import com.android.appcompose.database.model.ClassroomModel;
import com.android.appcompose.database.model.MentorModel;
import com.android.appcompose.network.AppRepository;
import com.android.appcompose.network.model.MentorResponse;
import com.android.appcompose.utils.DataType;

import java.util.List;

public class WelcomeViewModel extends AndroidViewModel implements OnCardGridItemClickListener {
    private static String TAG = "WelcomeViewModel";
    private LiveData<List<ClassroomModel>> localClassrooms;

    private LiveData<List<MentorModel>> localMentors;

    private LiveData<Object> selectedCategoryItem;
    private DataType selectedCategory;

    private AppRepository appRepository;




    public WelcomeViewModel(Application application){
        super(application);
        if(localClassrooms !=null){
            return;
        }

        appRepository = new AppRepository(application);
        localClassrooms = appRepository.getLocalClassrooms();
        localMentors = appRepository.getLocalMentors();
    }


    public void setLocalMentors(LiveData<List<MentorModel>> localMentors) {
        this.localMentors = localMentors;
    }


    public void setLocalClassrooms(LiveData<List<ClassroomModel>> localClassrooms) {
        this.localClassrooms = localClassrooms;
    }

    // Database Calls

    public LiveData<List<MentorModel>> getLocalMentors() {
        return localMentors;
    }

    public LiveData<List<ClassroomModel>> getLocalClassrooms() {
        return localClassrooms;
    }

    // Remote Calls

    public void getRemoteClassrooms(){
        appRepository.getRemoteClassrooms();
    }

    public void getRemoteMentors(){
        appRepository.getRemoteMentors();
    }


    //UI Click Hanndler

    @Override
    public void onCategoryClicked(DataType category) {
        this.selectedCategory = category;
    }

    @Override
    public void onItemClicked(Object item) {
        this.selectedCategoryItem = (LiveData<Object>) item;
    }




}