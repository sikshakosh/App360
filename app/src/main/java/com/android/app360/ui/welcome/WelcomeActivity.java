package com.android.app360.ui.welcome;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;

import com.android.app360.databinding.ActivityWelcomeBinding;

import com.android.appcompose.utils.DataType;
import com.android.appcompose.composable.utility.cardgrid.CardRecyclerViewAdapter;
import com.android.appcompose.composable.utility.cardgrid.CardGridRecyclerViewAdapter;
import com.android.appcompose.composable.utility.cardgrid.model.ParentModel;
import com.android.app360.ui.welcome.viewmodel.WelcomeViewModel;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.database.model.ClassroomModel;
import com.android.appcompose.database.model.MentorModel;


import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    private static String TAG = "WelcomeActivity";

    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;
    private ActivityWelcomeBinding binding;
    WelcomeViewModel welcomeViewModel;

    private CardGridRecyclerViewAdapter parentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;

    private static int DISPLAYED_CLASSROOM_COUNT = 0;
    private static int DISPLAYED_MENTOR_COUNT = 0;

    private final int GRID_ITEM_COUNT = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);

        binding = DataBindingUtil.setContentView(WelcomeActivity.this, R.layout.activity_welcome );

        binding.setLifecycleOwner(this);

        binding.setWelcomeViewModel(welcomeViewModel);

        imageSliderView = binding.bannerList;

        // Instantiate DotIndicator
        initDotIndicator(Color.TRANSPARENT);

        layoutSubviews();
        setupRecyclerView();
        parentModelArrayList.add(new ParentModel(DataType.FEATURED_CLASSROOMS));
        parentModelArrayList.add(new ParentModel(DataType.FEATURED_MENTORS));

        welcomeViewModel.getLocalClassrooms().observe(this, classrooms -> {
            if(classrooms.isEmpty()){
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        welcomeViewModel.getRemoteClassrooms();

                    }
                });
            }
            if(DISPLAYED_CLASSROOM_COUNT<GRID_ITEM_COUNT){
                ParentModel classroomParent = (ParentModel) parentModelArrayList.get(0);

                int counter = 0;

                for(ClassroomModel cm: classrooms){
                    if(DISPLAYED_CLASSROOM_COUNT<GRID_ITEM_COUNT){
                        classroomParent.getData().add(classrooms.get(counter));
                        DISPLAYED_CLASSROOM_COUNT +=1;
                        counter+=1;
                    }else{
                        break;
                    }

                }
                setupRecyclerView();
            }




        });

        welcomeViewModel.getLocalMentors().observe(this, mentors -> {
            if(mentors.isEmpty()){
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        welcomeViewModel.getRemoteMentors();

                    }
                });
            }

            if(DISPLAYED_MENTOR_COUNT<GRID_ITEM_COUNT){
                ParentModel classroomParent = (ParentModel) parentModelArrayList.get(1);

                int counter = 0;
                for(MentorModel cm: mentors){
                    if(DISPLAYED_MENTOR_COUNT<GRID_ITEM_COUNT){
                        classroomParent.getData().add(mentors.get(counter));
                        DISPLAYED_MENTOR_COUNT+=1;
                        counter +=1;
                    }else{
                        break;
                    }

                }
                setupRecyclerView();
            }


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

        if (parentAdapter == null) {
            parentAdapter = new CardGridRecyclerViewAdapter(parentModelArrayList, WelcomeActivity.this);
            parentLayoutManager = new LinearLayoutManager(this);

            binding.recyclerView.setLayoutManager(parentLayoutManager);
            binding.recyclerView.setAdapter(parentAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(),
                    1);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
            binding.recyclerView.addItemDecoration(dividerItemDecoration);


        } else {
            parentAdapter.notifyDataSetChanged();
        }
    }


}
