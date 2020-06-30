package com.example.appnhachoangha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.Service.MusicService;
import com.google.android.material.tabs.TabLayout;

import com.example.appnhachoangha.Adapter.MainViewPagerAdapter;
import com.example.appnhachoangha.Fragment.Fragment_Tim_Kiem;
import com.example.appnhachoangha.Fragment.Fragment_Trang_Chu;
import com.example.appnhachoangha.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView ivsongmini;
    TextView tvsongmini;
    ImageButton imgbuttonplaymini, imgbuttonnextmini, imgbuttonlovemini;
    LinearLayout llplaymini, llminiinfomationplay;

    MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;
    ArrayList<BaiHat> baiHats;
    private int indexSong = -1;

    private Runnable work;
    private final Handler handler = new Handler();

    //connect to the service
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicSrv = binder.getService();
            musicBound = true;

            // cật nhật giao diện play mini
            baiHats = new ArrayList<>();
            updateMiniSong();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    private void updateMiniSong() {
        work = new Runnable() {
            @Override
            public void run() {
                if (baiHats.size() > 0) {
                    if (musicSrv.isStatusDownload() && musicSrv.getIndexSong() != indexSong) {
                        indexSong = musicSrv.getIndexSong();
                        BaiHat baiHat = baiHats.get(musicSrv.getIndexSong());
                        Picasso.with(getApplicationContext()).load(baiHat.getHinhBaiHat()).into(ivsongmini);
                        tvsongmini.setText(baiHat.getTenBaiHat() + "\n" + baiHat.getCaSi());
                        llplaymini.setVisibility(View.VISIBLE);
                    }
                } else {
                    llplaymini.setVisibility(View.GONE);
                }
                handler.postDelayed(this, 300);
            }
        };
        handler.postDelayed(work,300);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        serviceNhac();
        anhxa();
        init();
        eventClick();
    }

    private void serviceNhac() {
        playIntent = new Intent(this, MusicService.class);
        bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        startService(playIntent);
    }

    private void anhxa(){
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        ivsongmini = findViewById(R.id.ivsongmini);
        tvsongmini = findViewById(R.id.tvsongmini);
        imgbuttonnextmini = findViewById(R.id.imgbuttonnextmini);
        imgbuttonplaymini = findViewById(R.id.imgbuttonplaymini);
        imgbuttonlovemini = findViewById(R.id.imgbuttonlovemini);
        llplaymini = findViewById(R.id.playmini);
        llminiinfomationplay = findViewById(R.id.llminiinfomationplay);
    }

    public void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Tìm Kiếm");

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void eventClick() {
        llminiinfomationplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayNhacActivity.class);
                startActivity(intent);
            }
        });

        imgbuttonplaymini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(musicSrv.isPlaying()){
                    musicSrv.pause();
                    imgbuttonplaymini.setImageResource(R.drawable.iconplay);
                }else{
                    musicSrv.start();
                    imgbuttonplaymini.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imgbuttonnextmini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicSrv.nextSong();
            }
        });

        imgbuttonlovemini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgbuttonlovemini.setImageResource(R.drawable.iconloved);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (musicBound) {
            baiHats = musicSrv.getBaiHats();
            if (musicSrv.isPlaying()) imgbuttonplaymini.setImageResource(R.drawable.iconpause);
            else imgbuttonplaymini.setImageResource(R.drawable.iconplay);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(musicConnection);
        handler.removeCallbacks(work);
    }

}
