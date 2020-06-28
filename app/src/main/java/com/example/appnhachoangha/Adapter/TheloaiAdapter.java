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

public class TheloaiAdapter extends RecyclerView.Adapter<TheloaiAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> mangTheLoai;

    public TheloaiAdapter(Context context, ArrayList<TheLoai> mangTheLoai) {
        this.context = context;
        this.mangTheLoai = mangTheLoai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai TheLoai = mangTheLoai.get(position);
        Picasso.with(context).load(TheLoai.getHinhTheLoai()).into(holder.imgHinhTheLoai);
    }

    @Override
    public int getItemCount() {
        if (mangTheLoai == null) return 0;
        return mangTheLoai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhTheLoai;
        public ViewHolder(View itemView){
            super(itemView);
            imgHinhTheLoai = itemView.findViewById(R.id.imgviewTheloai);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemTheloai", mangTheLoai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
