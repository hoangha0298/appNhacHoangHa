package com.example.appnhachoangha.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.appnhachoangha.Model.PlayList;
import com.example.appnhachoangha.R;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
    class  ViewHolder{
        TextView txtTenPaylist;
        ImageView imgBackground, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenPaylist = convertView.findViewById(R.id.txtviewTenPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imgviewPlaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imgviewBackgroundPlaylist);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhnen()).into(viewHolder.imgBackground);
        Picasso.with(getContext()).load(playList.getHinhicon()).into(viewHolder.imgPlaylist);
        viewHolder.txtTenPaylist.setText(playList.getTen());
        return  convertView;
    }
}
