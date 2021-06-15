package com.android.app360.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.android.appcompose.composable.utility.slider.indicator.DotIndicator;
import com.android.appcompose.composable.utility.slider.viewpager2.ImageSliderView;
import com.android.appcompose.ui.AppButton;
import com.android.app360.R;
import com.android.app360.ui.home.HomeActivity;


public class SplashActivity extends FragmentActivity {
    DotIndicator dotIndicator;
    ImageSliderView imageSliderView;
    AppButton skipButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageSliderView = findViewById(R.id.slider);

        // Instantiate DotIndicator
        initDotIndicator(Color.TRANSPARENT);
        initSkipButton();
        layoutSubviews();
    }

    private void initSkipButton(){
        skipButton = new AppButton(this,null,R.attr.borderlessButtonStyle);
        skipButton.setText(R.string.skip);
        skipButton.setRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.dark_gray)));
        skipButton.setBackgroundColor(getResources().getColor(R.color.white));
        skipButton.setTextColor(getResources().getColor(R.color.black));
        skipButton.setHeight(50);
        skipButton.setWidth(80);
        skipButton.setId(View.generateViewId());

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    void initDotIndicator(int bgColor){
        dotIndicator = new DotIndicator(this,imageSliderView.viewPager,bgColor);
        dotIndicator.setId(View.generateViewId());

    }

    void layoutSubviews(){
        ConstraintLayout parentLayout = (ConstraintLayout)findViewById(R.id.mainConstraint);
        ConstraintLayout.LayoutParams diParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        dotIndicator.setLayoutParams(diParams);
        parentLayout.addView(dotIndicator);

        ConstraintLayout.LayoutParams sbParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        skipButton.setLayoutParams(sbParams);
        parentLayout.addView(skipButton);


        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(parentLayout);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.BOTTOM, parentLayout.getId(), ConstraintSet.BOTTOM, 0);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.LEFT, parentLayout.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(dotIndicator.getId(), ConstraintSet.RIGHT, parentLayout.getId(), ConstraintSet.RIGHT, 0);

        constraintSet.connect(skipButton.getId(), ConstraintSet.BOTTOM, parentLayout.getId(), ConstraintSet.BOTTOM, 20);
        constraintSet.connect(skipButton.getId(), ConstraintSet.RIGHT, parentLayout.getId(), ConstraintSet.RIGHT, 20);


        constraintSet.applyTo(parentLayout);
    }


}