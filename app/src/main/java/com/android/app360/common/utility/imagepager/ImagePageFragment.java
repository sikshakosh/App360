package com.android.app360.common.utility.imagepager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.android.app360.R;


public class ImagePageFragment extends Fragment {
    private static final String TAG = "UserHome";
    private int fragmentImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =  inflater.inflate(R.layout.fragment_pager_data, container, false);
        int pos = getArguments().getInt("position");
        int drawable = getArguments().getInt("image");
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        img.setImageResource(drawable);

        return v;

    }

    public static ImagePageFragment newInstance(int position, int drawable){
        ImagePageFragment frg = new ImagePageFragment();
        Bundle b = new Bundle();
        b.putInt("position",position);
        b.putInt("image",drawable);
        frg.setArguments(b);
        return frg;
    }
}
