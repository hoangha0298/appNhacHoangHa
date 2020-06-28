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
import com.example.appnhachoangha.Model.Album;
import com.example.appnhachoangha.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> mangAlbum = null;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum){
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangAlbum.get(position);
        holder.txtTenAlbum.setText(album.getTenAlbum());
        holder.txtTenCaSi.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAlbum;
        TextView txtTenAlbum, txtTenCaSi;
        public ViewHolder(View itemView){
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.imgviewAlbum);
            txtTenAlbum = itemView.findViewById(R.id.txtviewTenAlbum);
            txtTenCaSi = itemView.findViewById(R.id.txtviewTenCaSiAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemAlbum", mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
