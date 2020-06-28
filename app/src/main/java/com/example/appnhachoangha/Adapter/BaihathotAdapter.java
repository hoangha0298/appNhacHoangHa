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

import org.w3c.dom.Text;

import java.util.ArrayList;

import com.example.appnhachoangha.Activity.PlayNhacActivity;
import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.R;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaihathotAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.txtCasi.setText(baiHat.getCaSi());
        holder.txtTen.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen, txtCasi;
        ImageView imgHinh;
        public  ViewHolder(View itemView){
            super(itemView);
            txtTen = (TextView) itemView.findViewById(R.id.txtviewTenbaihathot);
            txtCasi = (TextView) itemView.findViewById(R.id.txtviewTencasibaihathot);
            imgHinh = (ImageView) itemView.findViewById(R.id.imgviewBaihathot);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
