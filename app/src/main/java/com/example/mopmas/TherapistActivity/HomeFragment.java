package com.example.mopmas.TherapistActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mopmas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    Window window;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(Build.VERSION.SDK_INT>=21){
            window=getActivity().getWindow();
            window.setStatusBarColor(getActivity().getResources().getColor(R.color.gradEnd));


        }
        ImageSlider imageSlider = (ImageSlider) view.findViewById(R.id.image_slider);



        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider1));
        slideModels.add(new SlideModel(R.drawable.slider2));
        slideModels.add(new SlideModel(R.drawable.slider3));
        slideModels.add(new SlideModel(R.drawable.slider4));
        slideModels.add(new SlideModel(R.drawable.slider5));




        imageSlider.setImageList(slideModels,true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
