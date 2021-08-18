package com.android.app360.ui.welcome;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;
import com.android.app360.ui.welcome.adapter.ChildRecyclerViewAdapter;
import com.android.app360.ui.welcome.adapter.ParentRecyclerViewAdapter;
import com.android.app360.ui.welcome.model.ParentModel;
import com.android.app360.ui.welcome.viewmodel.HomeViewModel;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.network.model.Classroom;
import com.android.appcompose.network.model.Mentor;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static String TAG = "WelcomeActivity";
    public  static String SECTION_CLASSROOMS = "Featured Classrooms";
    public static String SECTION_MENTORS = "Featured Members";
    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;

    HomeViewModel homeViewModel;
    ArrayList<Classroom> classroomArrayList = new ArrayList<>();

    ChildRecyclerViewAdapter classroomAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView parentRecyclerView;
    private ParentRecyclerViewAdapter parentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        imageSliderView = findViewById(R.id.bannerList);
        parentRecyclerView   = findViewById(R.id.recyclerView);
        // Instantiate DotIndicator
        initDotIndicator(Color.TRANSPARENT);

        layoutSubviews();
        //setupRecyclerView();
        parentModelArrayList.add(new ParentModel(SECTION_CLASSROOMS));
        parentModelArrayList.add(new ParentModel(SECTION_MENTORS));



        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getLocalClassrooms().observe(this, classrooms -> {
            Log.d(TAG, "data received from db");
        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                homeViewModel.getRemoteClassrooms();
            }
        });

//        homeViewModel.getFeaturedClassroomsRepository().observe(this, featuredClassroom -> {
//            if(featuredClassroom!=null){
//                Log.d(TAG, "Responnse received"+featuredClassroom.getData());
//                List<Classroom> classroomList = featuredClassroom.getData();
//                ParentModel classroomParent = (ParentModel) parentModelArrayList.get(0);
//                for(int i=0;i<4;i++){
//                    classroomParent.getClassroomArray().add(classroomList.get(i));
//                }
//                setupRecyclerView();
//            }else {
//                Toast.makeText(this,"Something went wrong", Integer.parseInt("3000"));
//            }
//
//        });

//        homeViewModel.getFeaturedMentorsRepository().observe(this, featuredMentors -> {
//            if(featuredMentors!=null){
//                Log.d(TAG, "Responnse received"+featuredMentors.getData());
//                List<Mentor> classroomList = featuredMentors.getData();
//                ParentModel classroomParent = (ParentModel) parentModelArrayList.get(1);
//                for(int i=0;i<4;i++){
//                    classroomParent.getMentorArray().add(classroomList.get(i));
//                }
//
//                setupRecyclerView();
//            }else {
//                Toast.makeText(this,"Something went wrong", Integer.parseInt("3000"));
//            }
//
//        });

    }

    void initDotIndicator(int bgColor){
        dotIndicator = new DotIndicator(this,imageSliderView.viewPager,bgColor);
        dotIndicator.setId(View.generateViewId());

    }

    void layoutSubviews(){
        ConstraintLayout parentLayout = (ConstraintLayout)findViewById(R.id.mainConstraint);
        ConstraintLayout.LayoutParams diParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        dotIndicator.setLayoutParams(diParams);
        parentLayout.addView(dotIndicator);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(parentLayout);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.BOTTOM, imageSliderView.getId(), ConstraintSet.BOTTOM, 0);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.LEFT, imageSliderView.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.RIGHT, imageSliderView.getId(), ConstraintSet.RIGHT, 0);

        constraintSet.applyTo(parentLayout);
    }

    private void setupRecyclerView() {

        if (parentAdapter == null) {
            parentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, WelcomeActivity.this);
            parentLayoutManager = new LinearLayoutManager(this);

            parentRecyclerView.setLayoutManager(parentLayoutManager);
            parentRecyclerView.setAdapter(parentAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(parentRecyclerView.getContext(),
                    1);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
            parentRecyclerView.addItemDecoration(dividerItemDecoration);


        } else {
            parentAdapter.notifyDataSetChanged();
        }
    }


}
