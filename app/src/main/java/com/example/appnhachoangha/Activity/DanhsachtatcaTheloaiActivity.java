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
import com.example.appnhachoangha.Adapter.AllTheloaiAdapter;

import com.example.appnhachoangha.Model.TheLoai;
import com.example.appnhachoangha.R;
import com.example.appnhachoangha.Service.APIService;
import com.example.appnhachoangha.Service.DataService;

public class DanhsachtatcaTheloaiActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllTheloai;
    Toolbar toolbarTheloai;
    AllTheloaiAdapter allTheloaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_theloai);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetDanhsachAllTheloai();
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangTheloai = (ArrayList<TheLoai>) response.body();
                allTheloaiAdapter = new AllTheloaiAdapter(DanhsachtatcaTheloaiActivity.this, mangTheloai);
                recyclerViewAllTheloai.setLayoutManager(new GridLayoutManager(DanhsachtatcaTheloaiActivity.this, 2));
                recyclerViewAllTheloai.setAdapter(allTheloaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });

    }


    private void init() {
        recyclerViewAllTheloai = findViewById(R.id.recyclerviewAllTheloai);
        toolbarTheloai = findViewById(R.id.toolbarTheloai);
        setSupportActionBar(toolbarTheloai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Thể Loại");
        toolbarTheloai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
