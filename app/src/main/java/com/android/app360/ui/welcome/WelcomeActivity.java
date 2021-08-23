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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;
import com.android.appcompose.utils.DataType;
import com.android.appcompose.composable.utility.cardgrid.CardRecyclerViewAdapter;
import com.android.appcompose.composable.utility.cardgrid.CardGridRecyclerViewAdapter;
import com.android.appcompose.composable.utility.cardgrid.model.ParentModel;
import com.android.app360.ui.welcome.viewmodel.HomeViewModel;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.database.model.ClassroomModel;
import com.android.appcompose.database.model.MentorModel;
import com.android.appcompose.network.model.Classroom;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    private static String TAG = "WelcomeActivity";
    public  static String SECTION_CLASSROOMS = "Featured Classrooms";
    public static String SECTION_MENTORS = "Featured Members";
    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;

    HomeViewModel homeViewModel;
    ArrayList<Classroom> classroomArrayList = new ArrayList<>();

    CardRecyclerViewAdapter classroomAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView parentRecyclerView;
    private CardGridRecyclerViewAdapter parentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;

    private static int DISPLAYED_CLASSROOM_COUNT = 0;
    private static int DISPLAYED_MENTOR_COUNT = 0;

    private final int GRID_ITEM_COUNT = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        imageSliderView = findViewById(R.id.bannerList);
        parentRecyclerView   = findViewById(R.id.recyclerView);
        // Instantiate DotIndicator
        initDotIndicator(Color.TRANSPARENT);

        layoutSubviews();
        setupRecyclerView();
        parentModelArrayList.add(new ParentModel(SECTION_CLASSROOMS, DataType.FEATURED_CLASSROOMS));
        parentModelArrayList.add(new ParentModel(SECTION_MENTORS,DataType.FEATURED_MENTORS));
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getLocalClassrooms().observe(this, classrooms -> {
            if(classrooms.isEmpty()){
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        homeViewModel.getRemoteClassrooms();

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

        homeViewModel.getLocalMentors().observe(this, mentors -> {
            if(mentors.isEmpty()){
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        homeViewModel.getRemoteMentors();

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
