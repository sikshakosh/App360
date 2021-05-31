package com.android.app360.ui.splash;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.android.app360.R;
import com.android.appcompose.composable.utility.ImagePageFragment;
import com.android.appcompose.composable.utility.ImageZoomOutPageTransformer;

public class SplashActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    private int[] drawables = {R.drawable.splash_1,R.drawable.splash_2,R.drawable.splash_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        viewPager.setPageTransformer(new ImageZoomOutPageTransformer());
        pagerAdapter = new SplashActivity.ScreenSlidePagerAdapter(this,drawables);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        //private ArrayList<Integer> IMAGES = {R.drawable.background,R.drawable.background,R.drawable.background,R.drawable.background};

        private int[] listDrawables;
        public ScreenSlidePagerAdapter(FragmentActivity fa, int[] drawables) {
            super(fa);
            listDrawables = drawables;
        }

        @Override
        public ImagePageFragment createFragment(int position) {
            return  ImagePageFragment.newInstance(position, listDrawables[position]);
        }

        @Override
        public int getItemCount() {
            return listDrawables.length;
        }
    }
}