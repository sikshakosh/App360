package com.android.app360.ui.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.app360.R;
import com.android.app360.databinding.ActivityWelcomeBinding;
import com.android.app360.databinding.FragmentWelcomeBinding;
import com.android.app360.ui.login.LoginActivity;
import com.android.app360.ui.signup.SignupActivity;
import com.android.app360.ui.welcome.viewmodel.WelcomeViewModel;
import com.android.appcompose.composable.utility.cardgrid.CardGridRecyclerViewAdapter;
import com.android.appcompose.composable.utility.cardgrid.model.ParentModel;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.database.model.ClassroomModel;
import com.android.appcompose.database.model.MentorModel;
import com.android.appcompose.utils.DataType;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WelcomeFragment extends Fragment {

    private static String TAG = "WelcomeFragment";

    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;
    private FragmentWelcomeBinding binding;
    WelcomeViewModel welcomeViewModel;

    private CardGridRecyclerViewAdapter parentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;

    private static int DISPLAYED_CLASSROOM_COUNT = 0;
    private static int DISPLAYED_MENTOR_COUNT = 0;

    private final int GRID_ITEM_COUNT = 4;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment WelcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeFragment newInstance(String param1, String param2) {
        WelcomeFragment fragment = new WelcomeFragment();

        return fragment;
    }

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DISPLAYED_CLASSROOM_COUNT = 0;
        DISPLAYED_MENTOR_COUNT = 0;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome,container,false);
        welcomeViewModel = ((WelcomeActivity)getActivity()).getViewModel();
        parentModelArrayList.add(new ParentModel(DataType.FEATURED_CLASSROOMS));
        parentModelArrayList.add(new ParentModel(DataType.FEATURED_MENTORS));
        binding.setWelcomeViewModel(welcomeViewModel);
        binding.setLifecycleOwner(getActivity());
        imageSliderView = binding.bannerList;
        initDotIndicator(Color.TRANSPARENT);
        layoutSubviews();


        welcomeViewModel.getSelCategory().observe(getActivity(), item -> {
            Log.d("","Changed");
            ((WelcomeActivity)getActivity()).navigateTo(item.getType(),item);
        });

        welcomeViewModel.getSelCategoryItem().observe(getActivity(), item -> {
            Log.d("","Changed");

            ((WelcomeActivity)getActivity()).navigateTo(item.type,item);
        });

        welcomeViewModel.getLocalClassrooms().observe(getActivity(), classrooms -> {
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

        welcomeViewModel.getLocalMentors().observe(getActivity(), mentors -> {
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




        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    void initDotIndicator(int bgColor){
        dotIndicator = new DotIndicator(getActivity(),imageSliderView.viewPager,bgColor);
        dotIndicator.setId(View.generateViewId());

    }

    void layoutSubviews(){
        ConstraintLayout parentLayout = (ConstraintLayout)binding.mainConstraint;
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
            parentAdapter = new CardGridRecyclerViewAdapter(parentModelArrayList, getActivity(),welcomeViewModel);
            parentLayoutManager = new LinearLayoutManager(getActivity());

            binding.recyclerView.setLayoutManager(parentLayoutManager);
            binding.recyclerView.setAdapter(parentAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(),
                    1);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
            binding.recyclerView.addItemDecoration(dividerItemDecoration);


        } else {
            parentAdapter.notifyDataSetChanged();
        }
    }




}