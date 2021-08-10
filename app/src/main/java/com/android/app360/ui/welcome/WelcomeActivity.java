package com.android.app360.ui.welcome;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;
import com.android.app360.ui.welcome.adapter.ChildRecyclerViewAdapter;
import com.android.app360.ui.welcome.adapter.ParentRecyclerViewAdapter;
import com.android.app360.ui.welcome.model.ParentModel;
import com.android.app360.ui.welcome.viewmodel.ChildViewModel;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.network.Classroom;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static String TAG = "WelcomeActivity";
    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;

    ChildViewModel homeViewModel;
    ArrayList<Classroom> classroomArrayList = new ArrayList<>();

    ChildRecyclerViewAdapter classroomAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter parentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imageSliderView = findViewById(R.id.slider);
        parentRecyclerView   = findViewById(R.id.recyclerView);
        // Instantiate DotIndicator
        initDotIndicator(Color.TRANSPARENT);

        layoutSubviews();
        //setupRecyclerView();

        parentModelArrayList.add(new ParentModel("Featured Classrooms",new ArrayList<Classroom>()));
        parentModelArrayList.add(new ParentModel("Featured Classrooms",new ArrayList<Classroom>()));
        parentLayoutManager = new LinearLayoutManager(this);
        parentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, WelcomeActivity.this);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(parentAdapter);
        parentAdapter.notifyDataSetChanged();

        homeViewModel = new ViewModelProvider(this).get(ChildViewModel.class);
        homeViewModel.init();
        homeViewModel.getFClassroomsRepository().observe(this, featuredClassroom -> {
            Log.d(TAG, "Responnse received"+featuredClassroom.getData());
            List<Classroom> classroomList = featuredClassroom.getData();
            ParentModel classroomParent = (ParentModel) parentModelArrayList.get(0);
            classroomParent.setChildArray(classroomList);

            ParentModel classroomParent1 = (ParentModel) parentModelArrayList.get(1);
            classroomParent1.setChildArray(classroomList);
            //classroomArrayList.addAll(classroomList);
            //classroomAdapter.notifyDataSetChanged();
            parentAdapter.notifyDataSetChanged();
        });




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

        if (classroomAdapter == null) {
            classroomAdapter = new ChildRecyclerViewAdapter(this, classroomArrayList);
            //Initialize the RecyclerView
            //Set the Layout Manager
            //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);


            //Initialize the adapter and set it ot the RecyclerView
            classroomAdapter = new ChildRecyclerViewAdapter(this, classroomArrayList);
            mRecyclerView.setAdapter(classroomAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                    1);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
            mRecyclerView.addItemDecoration(dividerItemDecoration);
        } else {
            classroomAdapter.notifyDataSetChanged();
        }
    }


}
