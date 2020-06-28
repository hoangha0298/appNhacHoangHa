package com.example.appnhachoangha.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;


import de.hdodenhof.circleimageview.CircleImageView;
import com.example.appnhachoangha.R;

public class Fragment_Dia_Nhac extends Fragment {
    View view;
    CircleImageView circleImageView;
    public ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageView = view.findViewById(R.id.imgviewcircle);

        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation",0f,360f);
        objectAnimator.start();
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());

        return view;
    }

    public void PlayNhac(String hinhanh) {
        Picasso.with(getActivity()).load(hinhanh).into(circleImageView);
    }
//    public void Pause(String hinhanh){
//        objectAnimator.setRepeatMode(ValueAnimator);
//    }
//    public void PlayNhac(final String hinhanh) {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Picasso.with(getContext()).load(hinhanh).into(circleImageView);
//            }
//        }, 300);
//
//    }
}
