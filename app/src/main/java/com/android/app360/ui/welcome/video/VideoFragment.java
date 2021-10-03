package com.android.app360.ui.welcome.video;

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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app360.R;
import com.android.app360.databinding.FragmentVideoBinding;
import com.android.app360.databinding.FragmentWelcomeBinding;
import com.android.app360.ui.welcome.WelcomeActivity;

import com.android.app360.ui.welcome.home.WelcomeViewModel;
import com.android.appcompose.composable.utility.cardgrid.CardGridRecyclerViewAdapter;
import com.android.appcompose.composable.utility.cardgrid.model.ParentModel;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.database.model.ClassroomModel;
import com.android.appcompose.database.model.MentorModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    private static String TAG = "VideoFragment";


    private FragmentVideoBinding binding;
    VideoViewModel videoViewModel;

    private CardGridRecyclerViewAdapter parentAdapter;

    private RecyclerView.LayoutManager parentLayoutManager;

    private static int DISPLAYED_CLASSROOM_COUNT = 0;
    private static int DISPLAYED_MENTOR_COUNT = 0;

    private final int GRID_ITEM_COUNT = 4;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();

        return fragment;
    }

    public VideoFragment() {
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_video,container,false);
        parentLayoutManager = new LinearLayoutManager(getActivity());

        binding.recyclerView.setLayoutManager(parentLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(),
                1);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
        binding.recyclerView.addItemDecoration(dividerItemDecoration);

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_welcome, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setVideoViewModel(videoViewModel);
        binding.setLifecycleOwner(getActivity());
        bindEventToViewModel();


    }



    void bindEventToViewModel(){
        Log.d(TAG, "observer added");
        NavController navController = NavHostFragment.findNavController(this);
        NavBackStackEntry backStackEntry = navController.getBackStackEntry(R.id.home);
        videoViewModel = new ViewModelProvider(backStackEntry).get(VideoViewModel.class);

        videoViewModel.getParentModelData().observe(getViewLifecycleOwner(), new Observer<ArrayList<ParentModel>>() {
            @Override
            public void onChanged(ArrayList<ParentModel> parentModels) {
                if(videoViewModel.getGridAdapter().getValue()==null){
                    parentAdapter = new CardGridRecyclerViewAdapter(parentModels, 1);
                    parentAdapter.setClickListener(videoViewModel);
                    parentAdapter.setOrientation(RecyclerView.HORIZONTAL);
                    videoViewModel.setGridAdapter(parentAdapter);

                }
                binding.recyclerView.setAdapter(parentAdapter);


            }


        });


        videoViewModel.getSelCategory().observe(getViewLifecycleOwner(), new Observer<ParentModel>() {
            @Override
            public void onChanged(ParentModel model) {
                if(getViewLifecycleOwner().getLifecycle().getCurrentState()== Lifecycle.State.RESUMED){
                    ((WelcomeActivity)getActivity()).navigateTo(model.getType(),model);
                }

            }
        });

        videoViewModel.getSelCategoryItem().observe(getViewLifecycleOwner()  , item -> {
            Log.d("","Changed");

            ((WelcomeActivity)getActivity()).navigateTo(item.type,item);
        });

       videoViewModel.getLocalMentors().observe(getViewLifecycleOwner(), mentors -> {
            if(mentors.isEmpty()){
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        videoViewModel.getRemoteMentors();

                    }
                });
            }

           CardGridRecyclerViewAdapter adapter = (CardGridRecyclerViewAdapter)binding.recyclerView.getAdapter();
           setAdapter();
           ParentModel classroomParent = (ParentModel) parentAdapter.parentModelArrayList.get(0);

           int counter = 0;
           if(classroomParent.getData().size()==0){
               for(MentorModel cm: mentors){
                   classroomParent.getData().add(mentors.get(counter));
                   DISPLAYED_MENTOR_COUNT+=1;
                   counter +=1;

               }
           }

           setupRecyclerView();

        });
    }
    void setAdapter(){
        if(parentAdapter == null){
            parentAdapter = videoViewModel.getGridAdapter().getValue();
            binding.recyclerView.setAdapter(parentAdapter);
        }
    }

    private void setupRecyclerView() {

        parentAdapter.notifyDataSetChanged();
    }


}