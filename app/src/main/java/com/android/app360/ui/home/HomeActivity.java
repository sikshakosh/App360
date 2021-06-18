package com.android.app360.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.app360.ui.home.fragments.ClassroomFragment;
import com.android.app360.ui.home.fragments.HomeFragment;
import com.android.app360.ui.home.fragments.ConnectionFragment;
import com.android.app360.ui.home.fragments.NotificationFragment;
import com.android.app360.ui.home.fragments.PostFragment;
import com.android.appcompose.layout.tabs.AppFragmentPagerAdapter;
import com.android.appcompose.layout.tabs.AppTabLayout;
import com.android.appcompose.layout.tabs.TabType;
import com.android.app360.R;

public class HomeActivity extends AppCompatActivity {

    private AppTabLayout tabLayout;
    Toolbar toolbar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (AppTabLayout) findViewById(R.id.tab_host);

        setSupportActionBar(toolbar);



        setupTabLayout();
    }

    private void setupTabLayout(){
        HomeActivity parent = this;
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        getSupportActionBar().show();


                    break;
                    default:
                        getSupportActionBar().hide();
                }
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        tabLayout.setTextSize(18);
        tabLayout.setAllCaps(false);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setTabType(TabType.ICON_ONLY);
        tabLayout.setSelectedIndicatorColors(getResources().getColor(android.R.color.white));
        tabLayout.setActionBar(getSupportActionBar());
        tabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.black));
        tabLayout.setCustomFocusedColor(getResources().getColor(R.color.black));
        tabLayout.setCustomUnfocusedColor(getResources().getColor(R.color.dark_gray));
        tabLayout.setBackground(getResources().getDrawable(R.drawable.background_tab));
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                tabLayout.showIndicator(1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public static class TabAdapter extends AppFragmentPagerAdapter {

        private String[] titles = {
                "Home",
                "My Classrooms",
                "My Network",
                "Post",
                "Notifications",
        };

        private int[] icons = {
                R.drawable.ic_action_home,
                R.drawable.ic_action_classrooms,
                R.drawable.ic_action_network,
                R.drawable.ic_action_post,
                R.drawable.ic_action_menu
        };
        private Context context;

        public TabAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("Pos", position + 1);
            Fragment selectedFragment = null;
            switch (position){
                case 0:
                    //selectedFragment =  HomeFragment.newInstance("0", "Home Page");
                    selectedFragment =  HomeFragment.newInstance("0", "Home Page");
                    break;
                case 1:
                    selectedFragment =  ClassroomFragment.newInstance("0", "Classrooms Page");
                    break;
                case 2:
                    selectedFragment =  ConnectionFragment.newInstance("0", "Network Page");
                    break;
                case 3:
                    selectedFragment =  PostFragment.newInstance("0", "Post Page");
                    break;
                case 4:
                    selectedFragment =  NotificationFragment.newInstance("0", "Notification Page");
                    break;
                default:
                    return null;
            }

            selectedFragment.setArguments(bundle);

            return selectedFragment;
        }

        @Override
        public int getCount() {
            return icons.length == titles.length ? icons.length : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Drawable getPageDrawable(int position) {
            return ResourcesCompat.getDrawable(context.getResources(), icons[position], null);
        }

        @NonNull
        @Override
        public String getToolbarTitle(int position) {
            return titles[position];
        }
    }
}