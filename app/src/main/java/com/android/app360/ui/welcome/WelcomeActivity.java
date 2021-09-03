package com.android.app360.ui.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;

import com.android.app360.databinding.ActivityWelcomeBinding;

import com.android.app360.ui.login.LoginActivity;
import com.android.app360.ui.signup.SignupActivity;
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

   private WelcomeViewModel welcomeViewModel;
   private NavController navCtrl;

   private Toolbar toolbar=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        navCtrl = host.getNavController();
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navCtrl.getGraph()).build();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        welcomeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);
//
//
//
//        binding.setLifecycleOwner(this);
//
//        binding.setWelcomeViewModel(welcomeViewModel);
//
//        setSupportActionBar(binding.toolbar);
//
//
//
//        layoutSubviews();
//        setupRecyclerView();
//


    }

   public WelcomeViewModel getViewModel() {
        return welcomeViewModel;
   }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.welcome_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.signUp:
                navCtrl.navigate(R.id.action_welcomeFragment_to_moreListFragment);
//                intent = new Intent(this, SignupActivity.class);
//                this.startActivity(intent);
                return true;

            case R.id.login:
                intent = new Intent(this,LoginActivity.class);
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }
}
