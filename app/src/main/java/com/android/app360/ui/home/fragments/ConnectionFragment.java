package com.android.app360.ui.home.fragments;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app360.R;
import com.android.app360.ui.home.adapters.ConnectionItem;
import com.android.app360.ui.home.adapters.ConnectionItemAdapter;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerView;
    private ArrayList<ConnectionItem> mConnectionData;
    private ConnectionItemAdapter mAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ConnectionFragment() {
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
    public static ConnectionFragment newInstance(String param1, String param2) {
        ConnectionFragment fragment = new ConnectionFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_connection, container, false);
        //TextView tv = (TextView) view.findViewById(R.id.textView);
        //tv.setText("Home Page");
        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //Initialize the ArrayLIst that will contain the data
        mConnectionData = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new ConnectionItemAdapter(getActivity(), mConnectionData);
        mRecyclerView.setAdapter(mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                1);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        //Get the data
        initializeData();

        return view;
    }

    /**
     * Method for initializing the sports data from resources.
     */
    private void initializeData() {
        //Get the resources from the XML file
        String[] nameList = getResources().getStringArray(R.array.member_names);
        String[] classroomInfo = getResources().getStringArray(R.array.classroom_info);

        //Clear the existing data (to avoid duplication)
        mConnectionData.clear();

        //Create the ArrayList of Sports objects with the titles and information about each sport
        for(int i=0;i<nameList.length;i++){
            mConnectionData.add(new ConnectionItem(nameList[i],classroomInfo[i]));
        }

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();
    }


}