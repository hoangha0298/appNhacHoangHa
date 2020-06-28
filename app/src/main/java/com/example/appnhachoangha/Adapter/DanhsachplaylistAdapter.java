package com.example.appnhachoangha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.appnhachoangha.Activity.DanhsachbaihatActivity;
import com.example.appnhachoangha.Activity.DanhsachplaylistActivity;
import com.example.appnhachoangha.Model.PlayList;
import com.example.appnhachoangha.R;

public class DanhsachplaylistAdapter extends  RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<PlayList> mangplaylist;

    public DanhsachplaylistAdapter(Context context, ArrayList<PlayList> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = mangplaylist.get(position);
        Picasso.with(context).load(playList.getHinhnen()).into(holder.imgHinhnen);
        holder.txtTenplaylist.setText(playList.getTen());

    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhnen;
        TextView txtTenplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhnen = itemView.findViewById(R.id.imgviewDanhsachplaylist);
            txtTenplaylist = itemView.findViewById(R.id.txtviewTendanhsachplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist", mangplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
