package com.android.app360.ui.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.app360.R;

import com.android.app360.databinding.ActivityWelcomeBinding;
import androidx.appcompat.widget.AppCompatButton;

import com.android.appcompose.utils.DataType;
import com.android.appcompose.composable.utility.cardgrid.model.ParentModel;
import com.android.app360.ui.welcome.home.WelcomeViewModel;

public class  WelcomeActivity extends AppCompatActivity  {
    private static String TAG = "WelcomeFragment";
   private WelcomeViewModel welcomeViewModel;
   private NavController navCtrl;
   private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityWelcomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        welcomeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);
        binding.setWelcomeViewModel(welcomeViewModel);
            NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        navCtrl = host.getNavController();
       // NavBackStackEntry backStackEntry = navCtrl.getBackStackEntry(R.id.home);

        //appBarConfiguration = new AppBarConfiguration.Builder(navCtrl.getGraph()).build();
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home,R.id.find,R.id.video).build();



        setSupportActionBar(binding.toolbar);
        //NavigationUI.setupWithNavController(binding.toolbar, navCtrl, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNav, navCtrl);

        navCtrl.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()){
                    case R.id.videoFragment:
                        binding.toolbar.removeAllViews();
                        binding.toolbar.setTitle(R.string.video);

                        break;
                    case R.id.findFragment:
                        binding.toolbar.removeAllViews();
                        binding.toolbar.setTitle(R.string.find);

                        break;
                    case R.id.welcomeFragment:
                        binding.toolbar.removeAllViews();
                        AppCompatButton b1=new AppCompatButton(WelcomeActivity.this,null,R.style.TextAppearance_AppCompat_Title);
                        b1.setText(R.string.my_account);
                        b1.setTextColor(Color.WHITE);

                        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                        l3.gravity= Gravity.END;
                        b1.setLayoutParams(l3);
                        binding.toolbar.addView(b1);
                        binding.toolbar.setTitle(R.string.wideclassrooms);
                        break;
                    default:
                        Log.d(TAG,"NA");
                }

            }
        });

        

    }

   public WelcomeViewModel getViewModel() {
        return welcomeViewModel;
   }


   public void navigateTo(DataType type, Object data){
        switch (type){
            case FEATURED_MENTORS:
                if(data instanceof ParentModel){
                    navCtrl.navigate(R.id.action_welcomeFragment_to_dataListFragment);
                 }else{
                   // navCtrl.navigate(R.id.action_welcomeFragment_to_moreDetailFragment);
                    }

                break;
            case FEATURED_CLASSROOMS:
                if(data instanceof ParentModel){
                    navCtrl.navigate(R.id.action_welcomeFragment_to_dataListFragment);
                }else{
                    //navCtrl.navigate(R.id.action_welcomeFragment_to_moreDetailFragment);
                }
                break;
        }
   }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.search:
                navCtrl.navigate(R.id.action_welcomeFragment_to_dataListFragment);

                return true;

            case R.id.account:
//                intent = new Intent(this,LoginActivity.class);
//                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navCtrl.navigateUp();
    }
}
