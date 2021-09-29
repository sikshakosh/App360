package com.android.app360.ui.welcome.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app360.R;
import com.android.app360.databinding.FragmentAccountBinding;
import com.android.app360.databinding.FragmentWelcomeBinding;
import com.android.app360.ui.welcome.model.LoginUser;
import com.android.app360.ui.welcome.viewmodel.AccountViewModel;
import com.android.app360.ui.welcome.viewmodel.WelcomeViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static String TAG = "AccountFragment";
    private FragmentAccountBinding binding;
    private AccountViewModel accountViewModel;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindEventToViewModel();



    }

    void bindEventToViewModel() {
        Log.d(TAG, "observer added");
        NavController navController = NavHostFragment.findNavController(this);
        NavBackStackEntry backStackEntry = navController.getBackStackEntry(R.id.home);
        accountViewModel = new ViewModelProvider(backStackEntry).get(AccountViewModel.class);
        binding.setAccountViewModel(accountViewModel);
        binding.setLifecycleOwner(getActivity());
        accountViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<LoginUser>() {
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {
                if(getViewLifecycleOwner().getLifecycle().getCurrentState()== Lifecycle.State.RESUMED) {
                    if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress())) {
                        binding.txtEmailAddress.setError("Enter an E-Mail Address");
                        binding.txtEmailAddress.requestFocus();
                    } else if (!loginUser.isEmailValid()) {
                        binding.txtEmailAddress.setError("Enter a Valid E-mail Address");
                        binding.txtEmailAddress.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword())) {
                        binding.txtPassword.setError("Enter a Password");
                        binding.txtPassword.requestFocus();
                    } else if (!loginUser.isPasswordLengthGreaterThan5()) {
                        binding.txtPassword.setError("Enter at least 6 Digit password");
                        binding.txtPassword.requestFocus();
                    }
                }

            }
        });
        Log.d(TAG, "ACCOUNT MODEL SET");
    }
}