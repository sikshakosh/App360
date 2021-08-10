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
import com.android.app360.ui.home.adapters.ClassroomAdapter;
import com.android.app360.ui.home.viewmodel.HomeViewModel;
import com.android.app360.ui.welcome.adapter.WelcomeItemAdapter;
import com.android.app360.ui.welcome.viewmodel.WelcomeViewModel;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.network.Classroom;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static String TAG = "WelcomeActivity";
    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;

    WelcomeViewModel homeViewModel;
    ArrayList<Classroom> classroomArrayList = new ArrayList<>();
    WelcomeItemAdapter classroomAdapter;

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imageSliderView = findViewById(R.id.slider);
        mRecyclerView = findViewById(R.id.recyclerView);
        // Instantiate DotIndicator
        initDotIndicator(Color.TRANSPARENT);

        layoutSubviews();
        setupRecyclerView();

        homeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);
        homeViewModel.init();
        homeViewModel.getFClassroomsRepository().observe(this, featuredClassroom -> {
            Log.d(TAG, "Responnse received"+featuredClassroom.getData());
            List<Classroom> classroomList = featuredClassroom.getData();
            classroomArrayList.addAll(classroomList);
            classroomAdapter.notifyDataSetChanged();
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
            classroomAdapter = new WelcomeItemAdapter(this, classroomArrayList);
            //Initialize the RecyclerView
            //Set the Layout Manager
            //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);


            //Initialize the adapter and set it ot the RecyclerView
            classroomAdapter = new WelcomeItemAdapter(this, classroomArrayList);
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
