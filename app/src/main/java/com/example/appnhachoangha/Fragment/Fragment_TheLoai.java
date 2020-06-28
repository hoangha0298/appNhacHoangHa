package com.example.appnhachoangha.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.appnhachoangha.Activity.DanhsachtatcaAlbumActivity;
import com.example.appnhachoangha.Activity.DanhsachtatcaTheloaiActivity;
import com.example.appnhachoangha.Adapter.AlbumAdapter;
import com.example.appnhachoangha.Adapter.TheloaiAdapter;
import com.example.appnhachoangha.Model.Album;
import com.example.appnhachoangha.Model.TheLoai;
import com.example.appnhachoangha.R;
import com.example.appnhachoangha.Service.APIService;
import com.example.appnhachoangha.Service.DataService;

public class Fragment_TheLoai extends Fragment {
    View view;
    RecyclerView recyclerViewTheloai;
    TextView txtXemThemTheloai;
    TheloaiAdapter theloaiAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theloai, container,false);
        recyclerViewTheloai = view.findViewById(R.id.recyclerviewTheloai);
        txtXemThemTheloai = view.findViewById(R.id.txtviewXemThemTheloai);
        txtXemThemTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcaTheloaiActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetDataTheloai();
        callback.enqueue((new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theloaiArrayList = (ArrayList<TheLoai>) response.body();
                theloaiAdapter = new TheloaiAdapter(getActivity(), theloaiArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewTheloai.setLayoutManager(linearLayoutManager);
                recyclerViewTheloai.setAdapter(theloaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        }));
    }
}
