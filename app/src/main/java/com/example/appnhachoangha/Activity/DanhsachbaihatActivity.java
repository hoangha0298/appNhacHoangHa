package com.example.appnhachoangha.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.appnhachoangha.Adapter.DanhsachbaihatAdapter;
import com.example.appnhachoangha.Model.Album;
import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.Model.PlayList;
import com.example.appnhachoangha.Model.Quangcao;
import com.example.appnhachoangha.Model.TheLoai;
import com.example.appnhachoangha.R;
import com.example.appnhachoangha.Service.APIService;
import com.example.appnhachoangha.Service.DataService;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachbaihat;
    FloatingActionButton floatingActionButton;
    Quangcao quangcao;
    ImageView imgviewdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    PlayList playList;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        DataIntent();
        anhxa();
        init();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")){
            setValuewInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangCao());
        }
        if (playList != null && !playList.getTen().equals("")){
            setValuewInView(playList.getTen(), playList.getHinhnen());
            GetDataPlaylist(playList.getIdPlayList());
        }
        if (theLoai != null){
            setValuewInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")){
            setValuewInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private  void GetDataAlbum(String idalbum){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoalbum(idalbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    public void GetDataTheLoai(String idtheloai){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    public  void GetDataPlaylist(String idplaylist){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValuewInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgviewdanhsachcakhuc);
    }

    private void GetDataQuangcao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarDanhsach);
        recyclerViewDanhsachbaihat = findViewById(R.id.recyclerviewDanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgviewdanhsachcakhuc = findViewById(R.id.imgviewDanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangcao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
                //Toast.makeText(this, P.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("itemTheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("itemTheloai");

            }
            if(intent.hasExtra("itemAlbum")){
                album = (Album) intent.getSerializableExtra("itemAlbum");

            }
        }
    }
    private void eventclick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });
    }
}
