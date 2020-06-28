package com.example.appnhachoangha.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.R;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> mangbaihat){
        this.context = context;
        this.mangbaihat = mangbaihat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rowbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txtcasi.setText(baiHat.getCaSi());
        holder.txtindex.setText(position + 1 + "");
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txttenbaihat, txtcasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.txtviewtencasi);
            txtindex = itemView.findViewById(R.id.txtviewindex);
            txttenbaihat = itemView.findViewById(R.id.txtviewtenbaihat);
        }

    }
}
