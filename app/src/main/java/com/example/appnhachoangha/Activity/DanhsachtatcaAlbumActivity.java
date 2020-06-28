package com.example.appnhachoangha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.appnhachoangha.Adapter.AllAlbumAdapter;
import com.example.appnhachoangha.Adapter.DanhsachplaylistAdapter;
import com.example.appnhachoangha.Model.Album;
import com.example.appnhachoangha.Model.PlayList;
import com.example.appnhachoangha.R;
import com.example.appnhachoangha.Service.APIService;
import com.example.appnhachoangha.Service.DataService;

public class DanhsachtatcaAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllalbum;
    Toolbar toolbarAlbum;
    AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_album);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetDanhsachAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(DanhsachtatcaAlbumActivity.this, mangalbum);
                recyclerViewAllalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaAlbumActivity.this, 2));
                recyclerViewAllalbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });

    }


    private void init() {
        recyclerViewAllalbum = findViewById(R.id.recyclerviewAllAlbum);
        toolbarAlbum = findViewById(R.id.toolbarAlbum);
        setSupportActionBar(toolbarAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
