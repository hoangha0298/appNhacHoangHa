package com.example.appnhachoangha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhachoangha.Activity.PlayNhacActivity;
import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SearchbaihatAdapter extends RecyclerView.Adapter<SearchbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public SearchbaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat){
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_search_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baihat = mangbaihat.get(position);
        holder.txtTenbaihat.setText(baihat.getTenBaiHat());
        holder.txtCasi.setText(baihat.getCaSi());
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imgBaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbaihat, txtCasi;
        ImageView imgBaihat;

        public ViewHolder(View itemView){
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.txtviewSearchtenbaihat);
            txtCasi = itemView.findViewById(R.id.txtviewSearchcasi);
            imgBaihat = itemView.findViewById(R.id.imgviewSearchbaihat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", (Parcelable) mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
