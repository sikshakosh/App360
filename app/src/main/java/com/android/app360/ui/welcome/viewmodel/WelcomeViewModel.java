package com.android.app360.ui.welcome.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.appcompose.composable.utility.cardgrid.CardGridListener;
import com.android.appcompose.composable.utility.cardgrid.OnCardGridItemClickListener;
import com.android.appcompose.composable.utility.cardgrid.model.CardDataModel;
import com.android.appcompose.composable.utility.cardgrid.model.ParentModel;
import com.android.appcompose.database.model.ClassroomModel;
import com.android.appcompose.database.model.MentorModel;
import com.android.appcompose.network.AppRepository;
import com.android.appcompose.network.model.MentorResponse;
import com.android.appcompose.utils.DataType;

import java.util.ArrayList;
import java.util.List;

public class WelcomeViewModel extends AndroidViewModel implements CardGridListener {
    private static String TAG = "WelcomeViewModel";
    private LiveData<List<ClassroomModel>> localClassrooms;

    private LiveData<List<MentorModel>> localMentors;


    private MutableLiveData<ParentModel> selCategory = new MutableLiveData<ParentModel>();;
    private MutableLiveData<CardDataModel> selCategoryItem = new MutableLiveData<CardDataModel>();;
    private DataType selectedCategory;

    private AppRepository appRepository;
    private MutableLiveData<ArrayList<ParentModel>> mParentModelData;
    public MutableLiveData<ArrayList<ParentModel>> getParentModelData() {
        mParentModelData = new MutableLiveData<>();
        loadAllParentModel();
        return mParentModelData;
    }




    public WelcomeViewModel(Application application){
        super(application);
        if(localClassrooms !=null){
            return;
        }

        appRepository = new AppRepository(application);
        localClassrooms = appRepository.getLocalClassrooms();
        localMentors = appRepository.getLocalMentors();
    }

    private void loadAllParentModel() {
        ArrayList<ParentModel> arrayList = new ArrayList<>();
        arrayList.add(new ParentModel(DataType.FEATURED_CLASSROOMS));
        arrayList.add(new ParentModel(DataType.FEATURED_MENTORS));
        mParentModelData.postValue(arrayList);
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


    // Click Handlers
    public MutableLiveData<ParentModel> getSelCategory() {

        return selCategory;
    }

    public MutableLiveData<CardDataModel> getSelCategoryItem() {

        return selCategoryItem;
    }
    @Override
    public void onCardClicked(CardDataModel card) {
        Log.d("","Catergory Clicked");

        this.getSelCategoryItem().setValue(card);
    }

    @Override
    public void onMoreClicked(ParentModel category) {
        Log.d("","Catergory Clicked");

        this.getSelCategory().setValue(category);
    }
}