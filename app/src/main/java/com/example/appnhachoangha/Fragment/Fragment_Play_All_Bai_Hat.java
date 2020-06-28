package com.example.appnhachoangha.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhachoangha.Activity.PlayNhacActivity;
import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.R;

import com.example.appnhachoangha.Adapter.PlayNhacAdapter;
import com.example.appnhachoangha.Service.MusicService;

import java.util.ArrayList;

public class Fragment_Play_All_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewplaynhac;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_bai_hat,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recycleviewPlayAll);
        ArrayList<BaiHat> baiHats = PlayNhacActivity.mangbaihat;

        if (baiHats.size() > 0 ) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), baiHats);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playNhacAdapter);
        }
        return view;

    }
}
