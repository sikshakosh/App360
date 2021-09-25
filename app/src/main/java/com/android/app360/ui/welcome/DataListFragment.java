package com.android.app360.ui.welcome;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app360.R;

import com.android.app360.ui.welcome.viewmodel.WelcomeViewModel;

public class DataListFragment extends Fragment {

    private WelcomeViewModel welcomeViewModel;

    public static DataListFragment newInstance() {
        return new DataListFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.data_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LifecycleOwner owner = getViewLifecycleOwner();
        welcomeViewModel = ((WelcomeActivity)getActivity()).getViewModel();
        //welcomeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(owner, callback);


    }
}