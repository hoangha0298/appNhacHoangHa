package com.example.appnhachoangha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.appnhachoangha.Activity.DanhsachbaihatActivity;

import com.example.appnhachoangha.Model.TheLoai;
import com.example.appnhachoangha.R;

public class AllTheloaiAdapter extends RecyclerView.Adapter<AllTheloaiAdapter.ViewHolder>{
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public AllTheloaiAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_theloai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgviewalltheloai);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgviewalltheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewalltheloai = itemView.findViewById(R.id.imgviewallalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemTheloai", theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
