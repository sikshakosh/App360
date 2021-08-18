package com.android.app360.ui.home.fragments;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app360.R;
import com.android.app360.ui.home.viewmodel.HomeViewModel;
import com.android.app360.ui.home.adapters.ClassroomAdapter;
import com.android.appcompose.network.model.Classroom;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private final String TAG = "HomeFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    HomeViewModel homeViewModel;
    ArrayList<Classroom> classroomArrayList = new ArrayList<>();
    ClassroomAdapter classroomAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.init();
        homeViewModel.getFClassroomsRepository().observe(this, featuredClassroom -> {
            Log.d(TAG, "Responnse received"+featuredClassroom.getData());
            List<Classroom> classroomList = featuredClassroom.getData();
            classroomArrayList.addAll(classroomList);
            classroomAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

//        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        setupRecyclerView();

            return view;
    }


    private void setupRecyclerView() {
        if (classroomAdapter == null) {
            classroomAdapter = new ClassroomAdapter(this.getActivity(), classroomArrayList);
            //Initialize the RecyclerView
                //Set the Layout Manager
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


            //Initialize the adapter and set it ot the RecyclerView
            classroomAdapter = new ClassroomAdapter(getActivity(), classroomArrayList);
            mRecyclerView.setAdapter(classroomAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                    1);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
            mRecyclerView.addItemDecoration(dividerItemDecoration);
        } else {
            classroomAdapter.notifyDataSetChanged();
        }
    }



}