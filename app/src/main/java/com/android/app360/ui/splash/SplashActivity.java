package com.android.app360.ui.splash;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.android.app360.R;
import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;

import com.google.android.material.tabs.TabLayoutMediator;


public class SplashActivity extends FragmentActivity {
    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageSliderView = findViewById(R.id.slider);

        // Instantiate DotIndicator
        addDotIndicator(Color.TRANSPARENT);

    }

    void addDotIndicator(int bgColor){
        dotIndicator = new DotIndicator(this,imageSliderView.viewPager,bgColor);
        dotIndicator.setId(View.generateViewId());

        ConstraintLayout parentLayout = (ConstraintLayout)findViewById(R.id.mainConstraint);
        ConstraintLayout.LayoutParams serviceNameParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        dotIndicator.setLayoutParams(serviceNameParams);
        parentLayout.addView(dotIndicator);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(parentLayout);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.BOTTOM, parentLayout.getId(), ConstraintSet.BOTTOM, 0);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.LEFT, parentLayout.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.RIGHT, parentLayout.getId(), ConstraintSet.RIGHT, 0);
        constraintSet.applyTo(parentLayout);
    }


}