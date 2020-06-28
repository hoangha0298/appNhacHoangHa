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
import com.example.appnhachoangha.Adapter.DanhsachplaylistAdapter;
import com.example.appnhachoangha.Model.PlayList;
import com.example.appnhachoangha.R;
import com.example.appnhachoangha.Service.APIService;
import com.example.appnhachoangha.Service.DataService;

public class DanhsachplaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachplaylist;
    DanhsachplaylistAdapter danhsachplaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.GetDanhsachplaylist();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> mangplaylist = (ArrayList<PlayList>) response.body();
                danhsachplaylistAdapter = new DanhsachplaylistAdapter(DanhsachplaylistActivity.this, mangplaylist);
                recyclerViewdanhsachplaylist.setLayoutManager(new GridLayoutManager(DanhsachplaylistActivity.this, 2));
                recyclerViewdanhsachplaylist.setAdapter(danhsachplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbarDanhsachplaylist);
        recyclerViewdanhsachplaylist = findViewById(R.id.recyclerviewDanhsachplaylist);
    }
}
