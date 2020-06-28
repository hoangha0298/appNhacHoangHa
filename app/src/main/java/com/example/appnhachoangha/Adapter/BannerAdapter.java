package com.example.appnhachoangha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.appnhachoangha.Activity.DanhsachbaihatActivity;
import com.example.appnhachoangha.Model.Quangcao;
import com.example.appnhachoangha.R;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListbanner;
    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner){
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }
    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imgBackground = view.findViewById(R.id.imgviewbackgroundbanner);
        ImageView imgSongBanner = view.findViewById(R.id.imgviewBanner);
        TextView txtTitleSongBanner = view.findViewById(R.id.txtviewTitleBanner);
        TextView txtNoiDung = view.findViewById(R.id.txtNoiDung);

        Picasso.with(context).load(arrayListbanner.get(position).getHinhAnh()).into(imgBackground);
        Picasso.with(context).load(arrayListbanner.get(position).getHinhBaiHat()).into(imgSongBanner);
        txtTitleSongBanner.setText(arrayListbanner.get(position).getTenBaiHat());
        txtNoiDung.setText(arrayListbanner.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner", arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
